package com.example.appcocina.casosUso

import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.utils.AppCocina

class UsersUseCase {

    suspend fun getAllUsers():List<User>{
        return AppCocina.getDatabase().usersDao().getAllUsers()
    }

    suspend fun getEmailAndPass(email: String, pass: String): User {
        return AppCocina.getDatabase().usersDao().loginUser(email, pass)
    }

    suspend fun saveUsers(users: User) {
        AppCocina.getDatabase().usersDao().insertUsers(users)
    }

    suspend fun updateUsers(users: User) {
        AppCocina.getDatabase().usersDao().updateUsers(users)
    }

    suspend fun deleteUsers(users: User) {
        AppCocina.getDatabase().usersDao().deleteUsersById(users.id)
    }

    suspend fun getOneUser(id: Int): User {
        return AppCocina.getDatabase().usersDao().getUsersById(id)
    }
}
