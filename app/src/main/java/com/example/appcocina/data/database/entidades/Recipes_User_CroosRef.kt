package com.example.appcocina.data.database.entidades

import androidx.room.Entity

@Entity( tableName = "recipes_user", primaryKeys = ["id_Recipes", "id_User"])
data class Recipes_User_CroosRef(
    val id_Recipes: String,
    val id_User: String
)
