package com.example.appcocina.data.api.entidades

import com.example.appcocina.data.database.entidades.Categories

data class MealX(
    val strCategory: String,
    val strCategoryThumb: String? = null
)

fun MealX.toCategories() = Categories("",strCategory, strCategoryThumb)

