package com.example.appcocina.database.entidades

import java.util.*

data class User(var id: String = "-1L", var correo: String = "", var password: String = "",var nombre: String = "", var apellido: String = "",  var img: String = ""){

    constructor(correo: String, password: String) : this(){
        this.correo = correo
        this.password = password
        this.id = UUID.randomUUID().toString()
    }

}
