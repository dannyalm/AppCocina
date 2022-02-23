package com.example.appcocina.data.database.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "recipes")
@Serializable
data class Recipes(
    @PrimaryKey()
    val id: String,
    val nombre: String?,
    var img: String?,
    val direccion: String?,
    val video: String?,
    val ingredientes: String?,
    val cantidad: String?,
    val pasos: String?
)
