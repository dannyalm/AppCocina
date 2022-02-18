package com.example.appcocina.data.api.service

import com.example.appcocina.data.api.entidades.ListRecipesApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RecipesService {

    @GET
    suspend fun getAllRecipes(@Url url: String): Response<ListRecipesApi>

}