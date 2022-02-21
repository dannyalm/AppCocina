package com.example.appcocina.controladores

import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.logica.RecipesBL

class RecipesController {

    suspend fun getRecipesByCategory(category: String):List<Recipes>{
        return RecipesBL().getRecipesListByCategory(category)
    }

    suspend fun getRecipesByIngredient(ingredient: String):List<Recipes>{
        return RecipesBL().getRecipesListByIngredient(ingredient)
    }

    suspend fun getDetailsOneRecipe(id: String):List<Recipes>{
        return RecipesBL().getOneRecipeDetail(id)
    }

    suspend fun saveFavRecipes(recipes: Recipes) {
        RecipesBL().saveRecipeFavRecipes(recipes)
    }

    suspend fun deleteFavRecipes(recipes: Recipes) {
        RecipesBL().deleteRecipeFavRecipes(recipes)
    }

}