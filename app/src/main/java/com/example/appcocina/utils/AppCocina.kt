package com.example.appcocina.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.example.appcocina.data.database.RecipesDatabase

class AppCocina : Application() {

    companion object {
        private var db: RecipesDatabase? = null
        private lateinit var dbShare: SharedPreferences
        private lateinit var dbPreferences : SharedPreferences

        fun getDatabase(): RecipesDatabase {
            return db!!
        }

        fun getShareDB(): SharedPreferences {
            return dbShare
        }

        fun getPrefsDB(): SharedPreferences {
            return dbPreferences
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, RecipesDatabase::class.java, "recipes_DB")
            .build()

        dbShare = applicationContext.getSharedPreferences("prefsData", Context.MODE_PRIVATE)
        dbPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    }
}

