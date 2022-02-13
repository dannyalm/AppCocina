package com.example.appcocina.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appcocina.data.database.dao.IngredientsDAO
import com.example.appcocina.data.database.entidades.Ingredients

@Database(
    entities = [Ingredients::class],
    version = 1
)

abstract class RecipesDatabase : RoomDatabase() {
    abstract fun ingredientsDao(): IngredientsDAO
}