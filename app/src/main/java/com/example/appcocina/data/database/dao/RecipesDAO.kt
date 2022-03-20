package com.example.appcocina.data.database.dao

import androidx.room.*
import com.example.appcocina.data.database.entidades.Recipes

@Dao
interface RecipesDAO {

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipes>

    @Query("SELECT * FROM recipes WHERE id_Recipes = :idRecipes")
    suspend fun getRecipesById(idRecipes: String): Recipes?

    @Query("SELECT * FROM recipes WHERE autor = :idUser")
    suspend fun getCreatedRecipes(idUser: Int): List<Recipes>

    @Query("SELECT * FROM recipes WHERE categoria = :category AND autor != -1")
    suspend fun getAllCreatedRecipesByCategory(category: String): List<Recipes>

    @Query("SELECT * FROM recipes WHERE (ingrediente1 = :ingredient OR ingrediente2 = :ingredient OR ingrediente3 = :ingredient OR ingrediente4 = :ingredient OR ingrediente5 = :ingredient OR ingrediente6 = :ingredient OR ingrediente7 = :ingredient OR ingrediente8 = :ingredient OR ingrediente9 = :ingredient OR ingrediente10 = :ingredient OR ingrediente11 = :ingredient OR ingrediente12 = :ingredient OR ingrediente13 = :ingredient OR ingrediente14 = :ingredient OR ingrediente15 = :ingredient OR ingrediente16 = :ingredient OR ingrediente17 = :ingredient OR ingrediente18 = :ingredient OR ingrediente19 = :ingredient OR ingrediente20 = :ingredient) AND autor != -1")
    suspend fun getAllCreatedRecipesByIngredient(ingredient: String): List<Recipes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: Recipes)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRecipes(recipes: Recipes)

    @Delete()
    suspend fun deleteOneRecipe(recipes: Recipes)

    @Query("DELETE FROM recipes")
    suspend fun cleanDbRecipes()

    @Query("DELETE FROM recipes WHERE id_Recipes = :idRecipes")
    suspend fun deleteRecipesById(idRecipes: String)


}