package com.example.appcocina.data.api.entidades

import com.example.appcocina.data.database.entidades.Categories

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)

fun Category.toCategories() = Categories(idCategory,strCategory,strCategoryDescription,strCategoryThumb)