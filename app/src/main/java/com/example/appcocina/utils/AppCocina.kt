package com.example.appcocina.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.appcocina.data.database.RecipesDatabase

class AppCocina : Application() {

    companion object {
        private var db: RecipesDatabase? = null
        private lateinit var dbShare: SharedPreferences

        fun getDatabase(): RecipesDatabase {
            return db!!
        }

        fun getShareDB(): SharedPreferences {
            return dbShare!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, RecipesDatabase::class.java, "ingredients_DB")
            .build()

        dbShare = applicationContext.getSharedPreferences("login_data", Context.MODE_PRIVATE)
    }
}

