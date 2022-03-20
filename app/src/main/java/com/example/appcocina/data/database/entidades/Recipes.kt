package com.example.appcocina.data.database.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "recipes")
@Serializable
data class Recipes(
    @PrimaryKey()
    val id_Recipes: String,
    val nombre: String?,
    var img: String?,
    val direccion: String?,
    val video: String?,
    //val ingredientes: MutableList<String>?,
    var ingrediente1: String?,
    var cantidad1: String?,
    val ingrediente2: String?,
    val cantidad2: String?,
    val ingrediente3: String?,
    val cantidad3: String?,
    val ingrediente4: String?,
    val cantidad4: String?,
    val ingrediente5: String?,
    val cantidad5: String?,
    val ingrediente6: String?,
    val cantidad6: String?,
    val ingrediente7: String?,
    val cantidad7: String?,
    val ingrediente8: String?,
    val cantidad8: String?,
    val ingrediente9: String?,
    val cantidad9: String?,
    val ingrediente10: String?,
    val cantidad10: String?,
    val ingrediente11: String?,
    val cantidad11: String?,
    val ingrediente12: String?,
    val cantidad12: String?,
    val ingrediente13: String?,
    val cantidad13: String?,
    val ingrediente14: String?,
    val cantidad14: String?,
    val ingrediente15: String?,
    val cantidad15: String?,
    val ingrediente16: String?,
    val cantidad16: String?,
    val ingrediente17: String?,
    val cantidad17: String?,
    val ingrediente18: String?,
    val cantidad18: String?,
    val ingrediente19: String?,
    val cantidad19: String?,
    val ingrediente20: String?,
    val cantidad20: String?,
    val pasos: String?,
    var valoracion: Float?,
    val categoria: String?,
    val autor: Int?
)
{
    init {
        if (this.img == null) {
            this.img =
                "https://isabelpaz.com/wp-content/themes/nucleare-pro/images/no-image-box.png"
        }
    }
}
