package com.example.appcocina.controladores

import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.logica.RecipesUserBL

class RecipesUserController {

    suspend fun getFavRecipesUser(idUser: Int):  List<Recipes>{

        var listRecipes = RecipesUserBL().getRecipesUser(idUser)
        var listFavRecipes = mutableListOf<Recipes>()

        listRecipes.forEach {
            if(RecipesUserBL().checkIsSaved(it.id_Recipes, idUser)){
                listFavRecipes.add(it)
            }
        }

        return listFavRecipes
    }

}