package com.example.appcocina.logica

import com.example.appcocina.casosUso.CategoriesUseCase
import com.example.appcocina.data.database.entidades.Categories

class CategoriesBL {

    suspend fun getCategoriesList():List<Categories>{
        return CategoriesUseCase().getAllCategories()
    }

    //En caso que solo se quiera un ingrediente
    suspend fun getOneCategories(): Categories {
        val r = (0..3).random()
        return CategoriesUseCase().getAllCategories()[r]

    }
}