package com.example.appcocina.logica

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

    suspend fun deleteFavRecipesUser(idUser: Int) {
        RecipesUserUseCase().deleteRecipesUser(idUser)
    }
}