package com.example.appcocina.logica

import com.example.appcocina.casosUso.CategoriesUseCase
import com.example.appcocina.data.database.entidades.Categories

class CategoriesBL {

    suspend fun getCategoriesList():List<Categories>{
        return CategoriesUseCase().getAllCategories()
    }

}