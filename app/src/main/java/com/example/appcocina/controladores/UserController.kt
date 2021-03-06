package com.example.appcocina.controladores

import com.example.appcocina.casosUso.UsersUseCase
import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.logica.UserBL

class UserController {
    suspend fun LoginUser(email: String, pass: String): Boolean {
        return UserBL().LoginUser(
            email.trim().lowercase(),
            pass.trim().lowercase()
        )
    }

    suspend fun registerUser (user: User) {
        UserBL().saveRegisterUser(user)
    }

    suspend fun getLoginUser(email: String, pass: String): User {
        return UserBL().getLoginUser(
            email.trim().lowercase(),
            pass.trim().lowercase()
        )
    }

    suspend fun updateUser (user: User) {
        UserBL().updateRegisterUser(user)
    }

}