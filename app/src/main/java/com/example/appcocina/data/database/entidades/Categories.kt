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
    var img: String?
)
