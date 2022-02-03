package com.example.appcocina.logica

import com.example.appcocina.casosUso.IngredientsUseCase
import com.example.appcocina.database.entidades.Ingredients

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