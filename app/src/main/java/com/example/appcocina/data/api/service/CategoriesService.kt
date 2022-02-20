package com.example.appcocina.data.api.service

import com.example.appcocina.data.api.entidades.Category
import com.example.appcocina.data.api.entidades.ListCategoriesApi
import retrofit2.Response
import retrofit2.http.*

interface CategoriesService {

    @GET ("categories.php")
    suspend fun getAllCategories(): Response<ListCategoriesApi>

}