package com.example.appcocina.casosUso

import com.example.appcocina.data.api.CategoriesRetrofitAPI
import com.example.appcocina.data.api.RetrofitAPI
import com.example.appcocina.data.api.entidades.toCategories
import com.example.appcocina.data.api.service.CategoriesService
import com.example.appcocina.data.database.entidades.Categories

class CategoriesUseCase {

    suspend fun getAllCategories():List<Categories>{

        var resp: MutableList<Categories> = ArrayList<Categories>()
        val service = RetrofitAPI.getAPI().create(CategoriesService::class.java)
        val call = service.getAllCategories("categories.php")

        resp = if (call.isSuccessful){
            val body = call.body()
            body!!.categories.map {
                it.toCategories()
            }as MutableList<Categories>

        }else{
            ArrayList<Categories>()
        }

        return resp
        println(resp)
    }
}