package com.example.appcocina.casosUso

import com.example.appcocina.data.api.RetrofitAPI
import com.example.appcocina.data.api.entidades.toRecipes
import com.example.appcocina.data.api.service.RecipesService
import com.example.appcocina.data.database.entidades.Recipes


class RecipesUseCase {

    suspend fun getRecipesByCategory(category: String):List<Recipes>{
        val service = RetrofitAPI.getAPI().create(RecipesService::class.java)
        //OJO SE BUSCA POR EL NOMBRE DE LA CATEGORÍA
        val call = service.getAllRecipes("filter.php?c=$category")

        var resp = if (call.isSuccessful){
            return call.body()!!.meals.map {
                it.toRecipes()
            }
        }else (ArrayList<Recipes>())
        return resp
    }
    suspend fun getRecipesByIngredient(ingredient: String):List<Recipes>{
        val service = RetrofitAPI.getAPI().create(RecipesService::class.java)
        //OJO SE BUSCA POR EL NOMBRE DEL INGREDIENTE
        val call = service.getAllRecipes("filter.php?i=$ingredient")

        var resp = if (call.isSuccessful){
            return call.body()!!.meals.map {
                it.toRecipes()
            }
        }else (ArrayList<Recipes>())
        return resp
    }

    suspend fun getOneRecipe(id: String):List<Recipes>{
        val service = RetrofitAPI.getAPI().create(RecipesService::class.java)
        val call = service.getAllRecipes("lookup.php?i=$id")
        var resp = if (call.isSuccessful){
            return call.body()!!.meals.map {
                it.toRecipes()
            }
        }else (ArrayList<Recipes>())
        return resp
    }

}