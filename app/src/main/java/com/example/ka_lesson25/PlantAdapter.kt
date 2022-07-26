package com.example.ka_lesson25

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ka_lesson25.databinding.PlantItemBinding

class PlantAdapter: RecyclerView.Adapter<PlantAdapter.PlantHolder>() {

    val plantList = ArrayList<Plant>()

    class PlantHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding = PlantItemBinding.bind(item)
        //7.Cоздаем функцию bind():
        fun bind(plant:Plant) = with(binding) {
            im.setImageResource(plant.imageId)
            tvTitle.text = plant.title
        }
    }

    //три обязательных метода от наследуемого класса:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)
        return PlantHolder(view)
    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        holder.bind(plantList[position])
    }

    override fun getItemCount(): Int {
        return plantList.size
    }


    fun addPlant(plant: Plant) {
        plantList.add(plant)
        notifyDataSetChanged()
    }
}

