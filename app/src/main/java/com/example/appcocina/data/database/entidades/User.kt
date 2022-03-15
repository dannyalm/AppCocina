package com.example.appcocina.data.database.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.*

@Entity(tableName = "users")
@Serializable
data class User(
    var correo: String?,
    var contrasena: String?,
    var nombre: String?,
    var apellido: String?,
    var edad: Int,
    var sexo: String?,
    var img: String?)

{
    @PrimaryKey(autoGenerate = true)
    var id = 0

}
