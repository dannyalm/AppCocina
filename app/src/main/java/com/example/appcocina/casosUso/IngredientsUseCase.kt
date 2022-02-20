package com.example.appcocina.casosUso

import com.example.appcocina.data.api.RetrofitAPI
import com.example.appcocina.data.api.entidades.toIngredients
import com.example.appcocina.data.api.service.IngredientService
import com.example.appcocina.data.database.entidades.Ingredients

class IngredientsUseCase {

    suspend fun getAllIngredients():List<Ingredients>{

        val service = RetrofitAPI.getAPI().create(IngredientService::class.java)
        val call = service.getAllIngredient("list.php?i=list")
        var resp = if (call.isSuccessful){
            return call.body()!!.meals.map {
                it.toIngredients()
            }
        }else (ArrayList<Ingredients>())
        return resp
    }
}