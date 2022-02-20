package com.example.appcocina.data.api.entidades

import com.example.appcocina.data.database.entidades.Ingredients

data class Meal(
    val idIngredient: String,
    val strDescription: String,
    val strIngredient: String,
    val strIngredientThumb: String? = null,
    val strType: String
)

fun Meal.toIngredients() = Ingredients(idIngredient,strIngredient,strDescription,strIngredientThumb)