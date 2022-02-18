package com.example.appcocina.casosUso

import com.example.appcocina.data.api.RetrofitAPI
import com.example.appcocina.data.api.entidades.toRecipes
import com.example.appcocina.data.api.service.RecipesService
import com.example.appcocina.data.database.entidades.Recipes


class RecipesUseCase {

    suspend fun getAllRecipes():List<Recipes>{

        var resp: MutableList<Recipes> = ArrayList<Recipes>()
        val service = RetrofitAPI.getAPI().create(RecipesService::class.java)
        //OJO SE BUSCA POR ID
        val call = service.getAllRecipes("lookup.php?i=52772")

        resp = if (call.isSuccessful){
            val body = call.body()
            //meals hay que cambiar OJO
            body!!.meals.map {
                it.toRecipes()
            }as MutableList<Recipes>

        }else{
            ArrayList<Recipes>()
        }

        return resp
        println(resp)
    }
}