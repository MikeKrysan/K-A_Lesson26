package com.example.ka_lesson25

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ka_lesson25.databinding.ActivityEditBinding
import com.example.ka_lesson25.databinding.ActivityMainBinding

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    private var indexImage = 0  //1.Создадим переменную-счетчик, при нажатии на кнопку "next image" счетчик будет увеличиваться
    private var imageId = R.drawable.plant1 //4. Добавим переменную которая будет брать элементы из массива по мере увеличения счетчика (indexImage)
    private val imageIdList = listOf(   //3. Добавляем массив в EditActivity
        R.drawable.plant1,
        R.drawable.plant2,
        R.drawable.plant3,
        R.drawable.plant4,
        R.drawable.plant5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtons()      //12.Вызвали функцию initButtons()
    }


//6. Создаем функцию для кнопки "next image":
    private fun initButtons() = with(binding){
    //7. Создаем слушателя нажатий
        bNext.setOnClickListener {
            indexImage++    //8. При нажатии на кнопку увеличиваем индекс на 1
            if(indexImage > imageIdList.size-1) indexImage = 0  //9. Проверка чтобы не выйти за границу массива наших картинок
            imageId = imageIdList[indexImage]   //10. Присваиваем номер индекса для переменной, которая отвечает за выбранную картинку
            //11. *** Проверяем правильность присвоения индекса indexImage для переменной imageId через Log.d("MyLog", $idIndex ). Для чего нужно вызвать функцию initButtons() в методе onCreate()
            imageView.setImageResource(imageId)     //19. в imageView передается новый идентификатор, потому что он уже будет записан в imageId
        }
    //20. Передаем результат, создав нового слушателя нажатий для кнопки "Done":
        bDone.setOnClickListener {
            val plant = Plant(imageId, edTitle.text.toString(), edDisc.text.toString()) //21. Заполняем класс Plant
            //22. Передаем через intent заполненный класс Plant, для чего предварительно создадим этот intent:
            //Внуть созданного Intent() мы ничего не ложим, так как передавать данные будем с помощью putExtra()
            val editIntent = Intent().apply {
                putExtra("plant", plant)    //23. Когда мы передаем, нам не нужно указывать Serializable. Без разницы, что мы передаем, а когда мы принимаем, то нужно указать тип данных, который мы хотим принять
            }
            setResult(RESULT_OK, editIntent)    //24. Так как мы запустили EditActivity с ожидание результата в MainActivity (registerForActivityResult, и запустили с помощью лаунчера), мы возвращаем результат(интент и RESULT_OK)
            finish()    //25. Закрываем EditActivity. Как только закрывается EditActivity, результат отправляется на MainActivity и принимается в регистр registerForActivityResult
        }
    }
}

//***- Самопроверка в Logcat.