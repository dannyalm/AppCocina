package com.example.appcocina.logica

import com.example.appcocina.casosUso.RecipesUseCase
import com.example.appcocina.casosUso.RecipesUserUseCase
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.data.database.entidades.RecipesUserCroosRef

class RecipesUserBL {

    suspend fun getFavRecipesUser(idUser: Int):  List<Recipes>{
        return RecipesUserUseCase().getRecipesUser(idUser)
    }

    suspend fun saveFavRecipesUser(recipesUser: RecipesUserCroosRef) {
        RecipesUserUseCase().saveRecipesUser(recipesUser)
    }

    suspend fun deleteFavRecipesUser(idRecipe: String, idUser: Int) {
        RecipesUserUseCase().deleteRecipesUser(idRecipe, idUser)
    }

    suspend fun checkIsSaved(idReceta: String, idUsuario: Int): Boolean {
        val n = RecipesUserUseCase().getOneRecipeUser(idReceta, idUsuario)
        return n != null
    }
}