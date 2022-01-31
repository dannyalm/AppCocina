package com.example.appcocina.presentacion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.appcocina.R
import com.example.appcocina.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //Ocultar la barra
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //actionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.txtSignUp.setOnClickListener(){
            var intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        binding.btnIniciarSesion.setOnClickListener() {
            if (binding.txtEmail.text.toString().trim() == "") { //Elimino espacios en blanco
                binding.emailField.error = getString(R.string.error)
                println("commit 3")
            }
            else {
                binding.emailField.error = null
                var intent = Intent(this, PrincipalActivity::class.java)
                startActivity(intent)

            }
        }

        binding.txtForgotPassword.setOnClickListener(){
            Toast.makeText(this, "Funcion en construcción", Toast.LENGTH_LONG).show()
        }

        binding.loginPrincipal.setOnClickListener() {
            hideSoftKeyboard(binding.root)
        }

    }

    fun hideSoftKeyboard(view: View) {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}