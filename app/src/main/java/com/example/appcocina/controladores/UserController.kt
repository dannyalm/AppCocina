package com.example.appcocina.controladores

import com.example.appcocina.entities.Ingredients
import com.example.appcocina.entities.User
import com.example.appcocina.logica.IngredientsBL
import com.example.appcocina.logica.UserBL

class UserController {
    fun LoginUser(correo: String, pass: String): Boolean {
        return UserBL().LoginUser(
            correo.trim().uppercase(),
            pass.trim().lowercase()
        )
    }

    fun getOneUser(): User {
        return UserBL().getOneUser()
    }

}