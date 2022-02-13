package com.example.appcocina.data.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  IngredientsRetrofitAPI {

    fun getIngredientsAPI() : Retrofit{

        return Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}