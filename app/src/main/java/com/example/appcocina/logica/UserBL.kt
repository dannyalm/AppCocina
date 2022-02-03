package com.example.appcocina.logica

import com.example.appcocina.casosUso.UsersUseCase
import com.example.appcocina.database.entidades.User

class UserBL {

    fun LoginUser(email: String, pass: String): Boolean {
        var us = UsersUseCase().getEmailAndPass(email, pass)
        return (us.id == "-1L")
    }

    fun getUserList():List<User>{
        return UsersUseCase().getAllUsers()
    }

    fun getOneUser(): User {
        val r = (0)
        return UsersUseCase().getAllUsers()[r]

    }
}