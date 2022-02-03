package com.example.appcocina.logica

import com.example.appcocina.casoUso.IngredientsUseCase
import com.example.appcocina.entities.Ingredients

class IngredientsBL {

    fun getIngredientsList():List<Ingredients>{
        return IngredientsUseCase().getAllIngredients()
    }

    //En caso que solo se quiera un ingrediente
    fun getOneIngredients(): Ingredients {
        val r = (0..3).random()
        return IngredientsUseCase().getAllIngredients()[r]

    }
}