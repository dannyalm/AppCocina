package com.example.appcocina.data.database.dao

import androidx.room.*
import com.example.appcocina.data.database.entidades.User

@Dao
interface UsersDAO {

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE id_User = :idUsers")
    suspend fun getUsersById(idUsers: Int): User

    @Query("SELECT * FROM users WHERE correo=:email AND contrasena=:password")
    suspend fun loginUser(email: String, password: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUsers(users: User)

    @Delete()
    suspend fun deleteOneUser(users: User)

    @Query("DELETE FROM users")
    suspend fun cleanDbUsers()

    @Query("DELETE FROM users WHERE id_User = :idUsers")
    suspend fun deleteUsersById(idUsers: Int)

}