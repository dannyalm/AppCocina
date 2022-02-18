package com.example.appcocina.data.database.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "ingredients")
@Serializable
data class Ingredients(
    @PrimaryKey()
    val id: String,
    val nombre: String?,
    var img: String?
)
{
    init {
        this.img =
                "https://www.themealdb.com/images/ingredients/"+this.nombre+".png"
    }
}