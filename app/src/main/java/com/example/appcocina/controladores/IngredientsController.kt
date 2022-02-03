package com.example.appcocina.controladores

import com.example.appcocina.entities.Ingredients
import com.example.appcocina.logica.IngredientsBL

class IngredientsController {

    fun getOneIngredients(): Ingredients{
        return IngredientsBL().getOneIngredients()
    }

}