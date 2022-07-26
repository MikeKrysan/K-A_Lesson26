package com.example.ka_lesson25

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ka_lesson25.databinding.ActivityMainBinding

//Усложнение урока 26, нужно нажав на кнопку "Добавить растение", выбрать растение, дать ему название и описание.
//Для этого создадим дополнительный активити: EditActivity, выполним разметку экрана activity_edit.xml
//Когда в EditActivity мы нажмем на кнопку "Done", то передадим данные в MainActivity, будет создан заполненный шаблон с помощью recyclerView

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    private val adapter = PlantAdapter()
    private var editLauncher: ActivityResultLauncher<Intent>? = null    //13. Создадим переменную launcher, то-есть мы создадим registrForActivityResult и нам выдасть launcher, с помощью которого мы запустим activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        //14. Инициализируем лаунчер - запускаем функцию, которая будет ждать результата с другого активити, когда мы запустим этот лаунчер
        editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {    //15. Если с кодом, который передали, все в порядке, то можем обрабатывать данные:
                adapter.addPlant(it.data?.getSerializableExtra("plant") as Plant)   //16.** Мы хотим полученные растения с EditActivity, которые мы заполним (это класс Plant) передать в наш adapter
            }
        }
    }

    private fun init(){
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
            rcView.adapter = adapter
            buttonAdd.setOnClickListener {
                //2*
                editLauncher?.launch(Intent(this@MainActivity, EditActivity::class.java))   //18.При нажатии кнопки запускаем новое активити(EditActivity). this@MainActivity - ссылаемся на контекст нашего activity
            }
        }
    }
}

//2. Теперь при нажатии на кнопку у нас логика такая:
// нажал на кнопку - запускаем ожидание результата, и нас перебрасывает на EditActivity. На EditActivity заполняем все, жмем на кнопку "Done" и
// возвращаемся опять на это MainActivity. Здесь другая функция ждет результата, и мы передаем растение, которое заполнили и там обновляем
// адаптер (берем адаптер, и добавляем элемент, и он появляется в recyclerView)

//16.** Откуда мы берем растение? К нам приходит intent - это сообщение с другого activity(it). И мы получаем данные (data.getSerializableExtra() - сериализейбл потому что мы передаем не
// одно значение, мы хотим  передать целый класс). А чтобы передать целый класс, нужно класс также сделать serializable (класс превратится в поток байтов и который передастся через сообщение)
// ...getSerializableExtra("plant")...-перадаем ключевое слово "plant"
//... as Plant) ... - ждет класс Plant, а найдено Serializable, поэтому указываем как сериализайбл как класс Plant, потому что я знаю точно, что в виде сериализайбл я передам класс Plant
//...editLauncher?.launch(...- data может быть null, поэтому я указываю, что data точно будет не null(если data не null, попытатся получить тот класс, который мы передали)