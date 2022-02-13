package com.example.appcocina.data.api.service

import com.example.appcocina.data.api.entidades.ListIngredientsApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface IngredientService {

    @GET
    suspend fun getAllIngredient(@Url url: String): Response<ListIngredientsApi>



}