package com.example.appcocina.casosUso

import com.example.appcocina.data.api.RetrofitAPI
import com.example.appcocina.data.api.entidades.toRecipes
import com.example.appcocina.data.api.service.RecipesService
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.utils.AppCocina


class RecipesUseCase {

    suspend fun getRecipesAllByCategory(category: String):List<Recipes>{
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
    suspend fun getRecipesAllByIngredient(ingredient: String):List<Recipes>{
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

    suspend fun getRecipeDetail(id: String):List<Recipes>{
        val service = RetrofitAPI.getAPI().create(RecipesService::class.java)
        val call = service.getAllRecipes("lookup.php?i=$id")
        var resp = if (call.isSuccessful){
            return call.body()!!.meals.map {
                it.toRecipes()
            }
        }else (ArrayList<Recipes>())
        return resp
    }

    suspend fun getFavoritesRecipes(): List<Recipes> {
        val db = AppCocina.getDatabase()
        return db.recipesDao().getAllRecipes()
    }

    suspend fun saveRecipeFavRecipes(recipes: Recipes) {
        AppCocina.getDatabase().recipesDao().insertRecipes(recipes)
    }

    suspend fun deleteRecipeFavRecipes(recipes: Recipes) {
        AppCocina.getDatabase().recipesDao().deleteRecipesById(recipes.id)
    }

    suspend fun getOneRecipe(id: String): Recipes {
        return AppCocina.getDatabase().recipesDao().getRecipesById(id)
    }

}