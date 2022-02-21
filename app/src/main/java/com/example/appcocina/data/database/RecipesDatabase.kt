package com.example.appcocina.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appcocina.data.database.dao.IngredientsDAO
import com.example.appcocina.data.database.dao.RecipesDAO
import com.example.appcocina.data.database.entidades.Ingredients
import com.example.appcocina.data.database.entidades.Recipes

@Database(
    entities = [Ingredients::class, Recipes::class],
    version = 1
)

abstract class RecipesDatabase : RoomDatabase() {
    abstract fun ingredientsDao(): IngredientsDAO
    abstract fun recipesDao(): RecipesDAO
}