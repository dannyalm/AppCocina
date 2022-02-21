package com.example.appcocina.controladores

import com.example.appcocina.casosUso.CategoriesUseCase
import com.example.appcocina.data.database.entidades.Categories

class CategoriesController {

    suspend fun getCategories():List<Categories>{
        return CategoriesUseCase().getAllCategories()
    }

}