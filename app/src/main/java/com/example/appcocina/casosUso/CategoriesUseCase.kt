package com.example.appcocina.casosUso

import com.example.appcocina.data.api.RetrofitAPI
import com.example.appcocina.data.api.entidades.toCategories
import com.example.appcocina.data.api.service.CategoriesService
import com.example.appcocina.data.database.entidades.Categories

class CategoriesUseCase {

    suspend fun getAllCategories():List<Categories>{
        val service = RetrofitAPI.getAPI().create(CategoriesService::class.java)
        val call = service.getAllCategories()
        var resp = if (call.isSuccessful){
            return call.body()!!.categories.map {
                it.toCategories()
            }
        }else (ArrayList<Categories>())
        return resp
    }
}