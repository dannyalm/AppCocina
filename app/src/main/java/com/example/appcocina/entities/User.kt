package com.example.appcocina.entities

import java.util.*

data class User(var id: String = "-1L", var usuario: String = "", var correo: String = "", var password: String = "", var img: String = ""){

    constructor(correo: String, password: String) : this(){
        this.usuario = usuario
        this.correo = correo
        this.password = password
        this.img = img
        this.id = UUID.randomUUID().toString()
    }

}
