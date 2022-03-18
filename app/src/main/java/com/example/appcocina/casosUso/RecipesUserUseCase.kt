package com.example.appcocina.casosUso

import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.data.database.entidades.RecipesUserCroosRef
import com.example.appcocina.utils.AppCocina

class RecipesUserUseCase {


    suspend fun getRecipesUser(idUsuario: Int):  List<Recipes>{
        return AppCocina.getDatabase().recipesUserDao().getRecipesByUser(idUsuario).component1().recipes
    }

    suspend fun saveRecipesUser(recipesUser: RecipesUserCroosRef) {
        AppCocina.getDatabase().recipesUserDao().insertRecipesUser(recipesUser)
    }

    suspend fun deleteRecipesUser(idRecipe: String, idUser: Int) {
        AppCocina.getDatabase().recipesUserDao().deleteRecipesUser(idRecipe, idUser)
    }

    suspend fun getOneRecipeUser(idReceta: String, idUsuario: Int): RecipesUserCroosRef {
        return AppCocina.getDatabase().recipesUserDao().getRecipesUserById(idReceta, idUsuario)
    }

}