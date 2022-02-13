package com.example.appcocina.data.database.dao

import androidx.room.*
import com.example.appcocina.data.database.entidades.Ingredients

@Dao
interface IngredientsDAO {

    @Query("SELECT * FROM ingredients")
    suspend fun getAllIngredients(): List<Ingredients>

    @Query("SELECT * FROM ingredients WHERE id = :idIngredients")
    suspend fun getIngredientsById(idIngredients: Int): Ingredients

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(ingredients: Ingredients)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateIngredients(ingredients: Ingredients)

    @Delete()
    suspend fun deleteOneIngredients(ingredients: Ingredients)

    @Query("DELETE FROM ingredients")
    suspend fun cleanDbIngredients()

    @Query("DELETE FROM ingredients WHERE id = :idIngredients")
    suspend fun deleteIngredientsById(idIngredients: Int)

}