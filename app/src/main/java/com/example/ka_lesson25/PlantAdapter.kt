package com.example.ka_lesson25

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ka_lesson25.databinding.PlantItemBinding

class PlantAdapter: RecyclerView.Adapter<PlantAdapter.PlantHolder>() {  //унаследовались от RecyclerViewAdapter.Adapter<"здесь указваем viewHolder - ссылки на все view, которые будут в одном элементе. В нашем случае это внутренний класс PlantHolder">

    val plantList = ArrayList<Plant>()  //12. Создаем список для хранения данных

    class PlantHolder(item: View):RecyclerView.ViewHolder(item) {   //6.Создаем вложенный клас, который наследуется от RecyclerView.ViewHolder и этот viewHolder должен нести в себе тот элемент, который мы будем надувать: PlantHolder(item: View)
        val binding = PlantItemBinding.bind(item)   //9. создаем переменную, которая ссылается на binding-класс созданого шаблона. В данном случая не нужно надувать разметку через метод inflate(layoutInflater), так как разметка уже создана, и ее нужно только применить
        //7.Cоздаем функцию bind():
        fun bind(plant:Plant) = with(binding) {
            im.setImageResource(plant.imageId)
            tvTitle.text = plant.title
        }
    }

    //три обязательных метода от наследуемого класса:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)  //10.надуваем разметку*     . Разметка создана в памяти
        return PlantHolder(view)
    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) { //11. Здесь мы начинаем заполнять тот view, который мы создали и который находится в памяти (В нем есть сейчас пустой imageView и textView, а в этом методе мы эти контейнера заполняем).  Из созданной и надутой разметки и созданного экземпляра класса PlantHolder в onCreateHolder связываем с позицией в списке, который мы создали
        holder.bind(plantList[position])
    }

    override fun getItemCount(): Int {  //13. Чтобы адаптер знал, сколько элементов у него в списке, переопределяем метод getItemCount() от наследуемого класса RecyclerView.Adapter
        return plantList.size   //14. Теперь адаптер будет знать, сколько раз нужно запустить onCreateViewHolder и onBindViewHolder, для того чтобы заполнить шаблон разметки
    }


    fun addPlant(plant: Plant) {    //15.Нам нужно добавлять новый элемент
        plantList.add(plant)
        notifyDataSetChanged()  //16. Говорим адаптеру с помощью этой функции, что данные внутри изменились. Тогда адаптер проверяет список и добавляет еще один элемент
    }
}

//*Если бы мы находились на каком-нибудь activity, то разметку можно было бы взять напрямую через метод layoutInflater.inflate()