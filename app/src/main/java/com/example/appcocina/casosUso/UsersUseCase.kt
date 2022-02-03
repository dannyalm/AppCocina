package com.example.appcocina.casosUso

import com.example.appcocina.database.entidades.User

class UsersUseCase {

    private val usersDb = listOf<User>(
        User("gadiazr", "G1234"),
        User("dalmeidam", "D4321")
        )
    fun getEmailAndPass(email: String, pass: String): User {
        var us = User()

        usersDb.forEach() {
            if (it.correo == email && it.password == pass) {
                us = it
            }
        }
        return us
    }

    fun getAllUsers():List<User>{
        return usersDb
    }
}
