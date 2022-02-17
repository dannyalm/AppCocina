package com.example.appcocina.data.api.service

import com.example.appcocina.data.api.entidades.ListCategoriesApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CategoriesService {

    @GET
    suspend fun getAllCategories(@Url url: String): Response<ListCategoriesApi>

}