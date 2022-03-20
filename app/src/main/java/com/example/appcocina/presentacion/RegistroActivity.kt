package com.example.appcocina.presentacion

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.appcocina.R
import com.example.appcocina.controladores.UserController
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.databinding.ActivityLoginBinding
import com.example.appcocina.databinding.ActivityRegistroBinding
import com.example.appcocina.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.termsTxt.setOnClickListener() {
            var intent = Intent(this, TermsActivity::class.java)
            startActivity(intent)
        }

        binding.policiesTxt.setOnClickListener() {
            var intent = Intent(this, PolicyActivity::class.java)
            startActivity(intent)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }

        if (Build.VERSION.SDK_INT >= 21) {
            MainActivity.setWindowFlag(
                this,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                false
            )
            window.statusBarColor = Color.TRANSPARENT
        }

        binding.txtLogin.setOnClickListener() {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegistrar.setOnClickListener() {

            if (binding.txtEmail.text.toString().trim() == "" || binding.txtPassword.text.toString()
                    .trim() == "" ||
                binding.txtUsuario.text.toString()
                    .trim() == "" || binding.txtApellido.text.toString().trim() == ""
            ) { //Elimino espacios en blanco
                binding.emailField.error = "E-mail Required"
                binding.passwordField.error = "Password Required"
                binding.nombreField.error = "Name Required"
                binding.apellidoField.error = "Lastname Required"

            } else {

                if(Patterns.EMAIL_ADDRESS.matcher(binding.txtEmail.text).matches()){

                    binding.emailField.error = null
                    binding.passwordField.error = null
                    binding.apellidoField.error = null
                    binding.nombreField.error = null

                    var correo = binding.txtEmail.text.toString().trim().lowercase()
                    var contrasena = binding.txtPassword.text.toString().trim().lowercase()
                    var nombre = binding.txtUsuario.text.toString().trim()
                    var apellido = binding.txtApellido.text.toString().trim()

                    lifecycleScope.launch(Dispatchers.Main)
                    {
                        var us: User? = null

                        us = User(correo, contrasena, nombre, apellido, 0, "", null)

                        if (us != null) {
                            UserController().registerUser(us)
                        }
                    }

                    Toast.makeText(this, "Your account has been successfully created", Toast.LENGTH_LONG).show()
                    var intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this, "Please, introduce a valid e-mail", Toast.LENGTH_LONG).show()

                }
            }
        }

        binding.registroPrincipal.setOnClickListener() {
            hideSoftKeyboard(binding.root)
        }

    }

    fun hideSoftKeyboard(view: View) {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}