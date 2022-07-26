package com.example.ka_lesson25

import java.io.Serializable

data class Plant(val imageId: Int, val title: String, val desc: String):Serializable    //5. Добавляем данные в класс, потому что мы хотим сохранить фото, название и описание растения
//17. ...):Serializable ... -отмечаем, что класс должен включать в себя интерфейс Serializable