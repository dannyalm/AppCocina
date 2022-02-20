package com.example.appcocina.logica

import com.example.appcocina.casosUso.RecipesUseCase
import com.example.appcocina.data.database.entidades.Recipes


class RecipesBL {

    suspend fun getRecipesList(category: String):List<Recipes>{
        return RecipesUseCase().getRecipesByCategory(category)
    }

    suspend fun getRecipesListByIngredient(ingredient: String):List<Recipes>{
        return RecipesUseCase().getRecipesByIngredient(ingredient)
    }

    suspend fun getRecipe(id: String):List<Recipes>{
        return RecipesUseCase().getOneRecipe(id)
    }
}