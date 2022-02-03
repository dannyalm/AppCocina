package com.example.appcocina.logica

import com.example.appcocina.casoUso.IngredientsUseCase
import com.example.appcocina.casoUso.UsersUseCase
import com.example.appcocina.entities.Ingredients
import com.example.appcocina.entities.User

class UserBL {

    fun LoginUser(correo: String, pass: String): Boolean {
        var us = UsersUseCase().getEmailAndPass(correo, pass)
        return (us.id == "-1L")
    }

    fun getUserList():List<User>{
        return UsersUseCase().getAllUsers()
    }

    //En caso que solo se quiera un ingrediente
    fun getOneUser(): User {
        val r = (0)
        return UsersUseCase().getAllUsers()[r]

    }
}