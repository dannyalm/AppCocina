package com.example.appcocina.logica

import com.example.appcocina.casosUso.IngredientsUseCase
import com.example.appcocina.data.database.entidades.Ingredients

class IngredientsBL {

    suspend fun getIngredientsList():List<Ingredients>{
        return IngredientsUseCase().getAllIngredients()
    }

    //En caso que solo se quiera un ingrediente
    suspend fun getOneIngredients(): Ingredients {
        val r = (0..3).random()
        return IngredientsUseCase().getAllIngredients()[r]

    }
}