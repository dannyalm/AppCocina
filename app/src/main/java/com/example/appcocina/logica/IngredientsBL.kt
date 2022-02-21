package com.example.appcocina.logica

import com.example.appcocina.casosUso.IngredientsUseCase
import com.example.appcocina.data.database.entidades.Ingredients

class IngredientsBL {

    suspend fun getIngredientsList():List<Ingredients>{
        return IngredientsUseCase().getAllIngredients()
    }

}