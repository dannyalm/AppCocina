package com.example.appcocina.logica

import com.example.appcocina.casosUso.RecipesUseCase
import com.example.appcocina.casosUso.UsersUseCase
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.data.database.entidades.User

class UserBL {

    suspend fun LoginUser(email: String, pass: String): Boolean {
        var us = UsersUseCase().getEmailAndPass(email, pass)
        return us == null
    }

    suspend fun saveRegisterUser (user: User) {
        UsersUseCase().saveUsers(user)
    }


    suspend fun getLoginUser(email: String, pass: String): User {
        var us = UsersUseCase().getEmailAndPass(email, pass)
        return us
    }

    suspend fun updateRegisterUser (user: User) {
        UsersUseCase().updateUsers(user)
    }

}