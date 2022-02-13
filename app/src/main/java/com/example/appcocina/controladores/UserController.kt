package com.example.appcocina.controladores

import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.logica.UserBL

class UserController {
    fun LoginUser(email: String, pass: String): Boolean {
        return UserBL().LoginUser(
            email.trim(),
            pass.trim()
        )
    }

    fun getOneUser(): User {
        return UserBL().getOneUser()
    }

}