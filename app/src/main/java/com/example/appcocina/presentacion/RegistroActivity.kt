package com.example.appcocina.presentacion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.appcocina.R
import com.example.appcocina.databinding.ActivityLoginBinding
import com.example.appcocina.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtLogin.setOnClickListener(){
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegistrar.setOnClickListener() {
            if (binding.txtEmail.text.toString().trim() == "") { //Elimino espacios en blanco
                binding.emailField.error = getString(R.string.errorEmail)
            }
            else {
                binding.emailField.error = null
                Toast.makeText(this, "Su cuenta ha sido creada con éxito.", Toast.LENGTH_LONG).show()
                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

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