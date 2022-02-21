package com.example.appcocina.controladores

import com.example.appcocina.casosUso.IngredientsUseCase
import com.example.appcocina.data.database.entidades.Ingredients
import com.example.appcocina.logica.IngredientsBL

class IngredientsController {

    suspend fun getIngredients():List<Ingredients>{
        return IngredientsBL().getIngredientsList()
    }

}