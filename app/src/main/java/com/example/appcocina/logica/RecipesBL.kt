package com.example.appcocina.logica

import com.example.appcocina.casosUso.RecipesUseCase
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.utils.AppCocina


class RecipesBL {

    suspend fun getRecipesListByCategory(category: String):List<Recipes>{
        return RecipesUseCase().getRecipesAllByCategory(category)
    }

    suspend fun getRecipesListByIngredient(ingredient: String):List<Recipes>{
        return RecipesUseCase().getRecipesAllByIngredient(ingredient)
    }

    suspend fun getOneRecipeDetail(id: String):List<Recipes>{
        return RecipesUseCase().getRecipeDetail(id)
    }

    suspend fun checkIsSaved(id: String): Boolean {
        val n = RecipesUseCase().getOneRecipe(id)
        return n != null
    }

    suspend fun getValRecipe(id: String): Float? {
        return RecipesUseCase().getOneRecipe(id)?.valoracion
    }


    suspend fun getFavoritesRecipes(): List<Recipes> {
        return RecipesUseCase().getFavoritesRecipes()
    }

    suspend fun saveRecipeFavRecipes(recipes: Recipes) {
        RecipesUseCase().saveRecipeFavRecipes(recipes)
    }

    suspend fun deleteRecipeFavRecipes(recipes: Recipes) {
        RecipesUseCase().deleteRecipeFavRecipes(recipes)
    }

    suspend fun getCreatedRecipes(idUser: Int): List<Recipes> {
        return RecipesUseCase().getCreatedRecipes(idUser)
    }

    suspend fun getOneRecipe(id: String): Recipes? {
        return RecipesUseCase().getOneRecipe(id)
    }

}