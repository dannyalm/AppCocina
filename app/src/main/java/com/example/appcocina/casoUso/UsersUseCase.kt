package com.example.appcocina.casoUso

import com.example.appcocina.entities.Ingredients
import com.example.appcocina.entities.User

class UsersUseCase {

    private val usersDb = listOf<User>(
        User(
            "",
            "admin",
            "admin",
            "admin",
            "https://thumbs.dreamstime.com/b/inicio-de-sesi%C3%B3n-administrador-en-el-icono-del-port%C3%A1til-vector-stock-166205404.jpg"
            ),
        User(
            "xxx",
            "xxx"
        )
    )

    fun getEmailAndPass(correo: String, pass: String): User {
        var us = User()

        usersDb.forEach() {
            if (it.correo == correo && it.password == pass) {
                us = it
            }
        }
        return us
    }

    fun getAllUsers():List<User>{
        return usersDb
    }
}
