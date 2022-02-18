package com.example.appcocina.data.database.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "categories")
@Serializable
data class Categories(
    @PrimaryKey()
    val id: String,
    val nombre: String?,
    val descripcion: String?,
    var img: String?
)
{
    init {
        if (this.img == null) {
            this.img =
                "https://isabelpaz.com/wp-content/themes/nucleare-pro/images/no-image-box.png"
        }
    }
}