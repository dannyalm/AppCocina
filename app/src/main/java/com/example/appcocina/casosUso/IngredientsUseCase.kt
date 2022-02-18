package com.example.appcocina.casosUso

import com.example.appcocina.data.api.IngredientsRetrofitAPI
import com.example.appcocina.data.api.RetrofitAPI
import com.example.appcocina.data.api.entidades.toIngredients
import com.example.appcocina.data.api.service.IngredientService
import com.example.appcocina.data.database.entidades.Ingredients

class IngredientsUseCase {

    suspend fun getAllIngredients():List<Ingredients>{

        var resp: MutableList<Ingredients> = ArrayList<Ingredients>()
        val service = RetrofitAPI.getAPI().create(IngredientService::class.java)
        val call = service.getAllIngredient("list.php?i=list")

        resp = if (call.isSuccessful){
            val body = call.body()
            body!!.meals.map {
                it.toIngredients()
            }as MutableList<Ingredients>

        }else{
            ArrayList<Ingredients>()
        }

        return resp
        println(resp)
    }
}