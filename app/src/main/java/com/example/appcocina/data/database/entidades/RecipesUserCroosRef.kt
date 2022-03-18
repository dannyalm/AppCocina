package com.example.appcocina.data.database.entidades

import androidx.room.Entity

@Entity( tableName = "recipes_user", primaryKeys = ["id_Recipes", "id_User"])
data class RecipesUserCroosRef(
    var id_Recipes: String,
    var id_User: Int
)
