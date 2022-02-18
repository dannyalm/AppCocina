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
    val dir: String?,
    val vid: String?,
    val ingre: String?,
    val pasos: String?
)
