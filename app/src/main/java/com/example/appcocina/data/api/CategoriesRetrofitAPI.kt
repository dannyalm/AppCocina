package com.example.appcocina.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CategoriesRetrofitAPI {

    fun getCategoriesAPI() : Retrofit {

        return Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}