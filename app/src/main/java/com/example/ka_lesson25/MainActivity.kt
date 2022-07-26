package com.example.ka_lesson25

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ka_lesson25.databinding.ActivityMainBinding

//1. Создали recyclerView разметку в activity_main.xml
//2. В build.gradle добавили bindingView
//3. Создали класс данных(класс-модель для заполнения шаблона) - data class Plant
//4. Добавляем картинки в drawable
//5. Создаем adapterView для заполнения шаблона данными - PlantAdapter.
//8. Создаем форму шаблона - plant_item.xml
//24. Создаем кнопку "Add Plant" для main_activity

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding    //17. Подключаем binding также и для главного activity
    private val adapter = PlantAdapter()    //21. Создаем адаптер - экземпляр класса PlantAdapter
    private val imageIdList = listOf(   //26. Создаем массив с идентификаторами растений для возможности автоматически добавлять новые значения после окончания списка
        R.drawable.plant1,
        R.drawable.plant2,
        R.drawable.plant3,
        R.drawable.plant4,
        R.drawable.plant5)

    private var index = 0   //27. Переменная, которая будет увеличивать индекс с каждым нажатием на кнопку

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)   //18.Надуваем binding
        setContentView(binding.root)
        init()  //23. Запускаем функцию инициализации. На данном этапе все верно, но список пустой, не заполненый, поэтому нужно добавить кнопку добавления шаблонов в activity_main.xml и повесить на нее слушателя.
    }

    private fun init(){     //19. Создаем фунцию для инициализации RecyclerView
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)  //20. Произвели настройку заполнения recyclerView
            rcView.adapter = adapter    //22 Выбираем адаптер
            //25.Обработчик слушателя кнопки "Добавить растение"
            buttonAdd.setOnClickListener{
                if(index > 4) index = 0 //30. Создаем условие, чтобы можно было переиспользовать картинки по кругу
                val plant = Plant(imageIdList[index], "Plant $index")   //28.создаем экземпляры класса Plant. Заполняем экземпляры, передавая соответствующую картинку и надпись
                adapter.addPlant(plant) 
                index++ //29.увеличиваем индекс на 1
            }
        }
    }
}