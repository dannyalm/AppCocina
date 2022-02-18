package com.example.appcocina.logica

import com.example.appcocina.casosUso.RecipesUseCase
import com.example.appcocina.data.database.entidades.Recipes


class RecipesBL {

    suspend fun getRecipesList():List<Recipes>{
        return RecipesUseCase().getAllRecipes()
    }

    //En caso que solo se quiera uno
    suspend fun getOneRecipes(): Recipes {
        val r = (0..3).random()
        return RecipesUseCase().getAllRecipes()[r]

    }
}