package com.example.appcocina.logica

import com.example.appcocina.casosUso.RecipesUseCase
import com.example.appcocina.casosUso.RecipesUserUseCase
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.data.database.entidades.RecipesUserCroosRef
import com.example.appcocina.utils.AppCocina

class RecipesUserBL {

    suspend fun getRecipesUser(idUser: Int):  List<Recipes>{
        return RecipesUserUseCase().getRecipesUser(idUser)
    }

    suspend fun saveFavRecipesUser(recipesUser: RecipesUserCroosRef) {
        RecipesUserUseCase().saveRecipesUser(recipesUser)
    }

    suspend fun deleteFavRecipesUser(idRecipe: String, idUser: Int) {
        RecipesUserUseCase().deleteRecipesUser(idRecipe, idUser)
    }

    suspend fun getValRecipeUser(idReceta: String, idUsuario: Int): Float? {
        return RecipesUserUseCase().getOneRecipeUserById(idReceta, idUsuario)?.valoracion
    }

    suspend fun checkIsSaved(idReceta: String, idUsuario: Int): Boolean {
        val n = RecipesUserUseCase().getOneRecipeUser(idReceta, idUsuario)
        return n != null
    }

    suspend fun countRecipeById(idRecipe: String): Int {
        return RecipesUserUseCase().countRecipe(idRecipe)
    }

    suspend fun sumRecipeById(idRecipe: String): Int {
        return RecipesUserUseCase().sumRecipe(idRecipe)
    }

    fun ratingRecipes(sumRegistros: Float, lastRating: Float, newRating: Float, numRegistros: Float): Float {
        val resultado = (sumRegistros-lastRating+newRating)/numRegistros
        return resultado
    }

}