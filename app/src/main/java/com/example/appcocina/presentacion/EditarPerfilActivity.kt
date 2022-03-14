package com.example.appcocina.presentacion

import android.R
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.databinding.ActivityEditarPerfilBinding
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class EditarPerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var us: User? = null
        intent.extras?.let {
            us = Json.decodeFromString<User>(it.getString("usuario").toString())
        }
        if (us != null) {
            loadInformation(us!!)
        }

        binding.btnAtras.setOnClickListener(){
            onBackPressed()
        }

    }

    fun loadInformation(user: User) {
        binding.txtUsuario.text = Editable.Factory.getInstance().newEditable(user.nombre)
        binding.txtApellido.text = Editable.Factory.getInstance().newEditable(user.apellido)
        binding.txtEmail.text = Editable.Factory.getInstance().newEditable(user.correo)
        binding.txtPassword.text = Editable.Factory.getInstance().newEditable(user.contrasena)
    }
}