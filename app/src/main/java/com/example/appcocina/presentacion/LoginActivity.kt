package com.example.appcocina.presentacion

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.appcocina.R
import com.example.appcocina.controladores.UserController
import com.example.appcocina.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var access: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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


        binding.txtSignUp.setOnClickListener(){
            var intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        binding.btnIniciarSesion.setOnClickListener() {

            lifecycleScope.launch {
                access = UserController().LoginUser(
                    binding.txtEmail.text.toString(),
                    binding.txtPassword.text.toString()
                )
            }

            if (access) {
                binding.emailField.error = getString(R.string.errorEmail)
                binding.passwordField.error = getString(R.string.errorPassword)
            }
            else {
                binding.emailField.error = null
                binding.passwordField.error = null
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