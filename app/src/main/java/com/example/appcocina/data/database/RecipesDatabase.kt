package com.example.appcocina.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appcocina.data.database.dao.IngredientsDAO
import com.example.appcocina.data.database.dao.RecipesDAO
import com.example.appcocina.data.database.dao.RecipesUserDAO
import com.example.appcocina.data.database.dao.UsersDAO
import com.example.appcocina.data.database.entidades.Ingredients
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.data.database.entidades.RecipesUserCroosRef
import com.example.appcocina.data.database.entidades.User


@Database(
    entities = [Ingredients::class, Recipes::class, User::class, RecipesUserCroosRef::class],//, Converters::class],
    version = 1
)
//@TypeConverters(Converters::class)


abstract class RecipesDatabase : RoomDatabase() {
    abstract fun ingredientsDao(): IngredientsDAO
    abstract fun recipesDao(): RecipesDAO
    abstract fun usersDao(): UsersDAO
    abstract fun recipesUserDao(): RecipesUserDAO
}