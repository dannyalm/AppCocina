package com.example.appcocina.data.database.dao

import androidx.room.*
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.data.database.entidades.RecipesUserCroosRef
import com.example.appcocina.data.database.entidades.User_Recipes_Relation

@Dao
interface RecipesUserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipesUser(recipesUser: RecipesUserCroosRef)

    @Query("DELETE FROM recipes_user WHERE id_Recipes = :idRecipe AND id_User = :idUser")
    suspend fun deleteRecipesUser(idRecipe: String, idUser: Int)

    @Query("SELECT * FROM recipes_user WHERE id_Recipes = :idRecipes AND id_User = :idUser")
    suspend fun getRecipesUserById(idRecipes: String, idUser: Int): RecipesUserCroosRef

    //Relación Muchos a Muchos
    @Transaction
    @Query("SELECT * FROM users WHERE id_User = :idUser")
    suspend fun getRecipesByUser(idUser: Int): List <User_Recipes_Relation>

}