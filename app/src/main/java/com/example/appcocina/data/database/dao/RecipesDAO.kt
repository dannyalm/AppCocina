package com.example.appcocina.data.database.dao

import androidx.room.*
import com.example.appcocina.data.database.entidades.Recipes

@Dao
interface RecipesDAO {

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipes>

    @Query("SELECT * FROM recipes WHERE id = :idRecipes")
    suspend fun getRecipesById(idRecipes: String): Recipes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: Recipes)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRecipes(recipes: Recipes)

    @Delete()
    suspend fun deleteOneRecipe(recipes: Recipes)

    @Query("DELETE FROM recipes")
    suspend fun cleanDbRecipes()

    @Query("DELETE FROM recipes WHERE id = :idRecipes")
    suspend fun deleteRecipesById(idRecipes: String)

}