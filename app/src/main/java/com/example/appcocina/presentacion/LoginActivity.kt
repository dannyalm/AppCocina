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
import com.example.appcocina.data.database.entidades.Ingredients
import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import android.text.TextUtils
import android.util.Patterns


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

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
                var access = UserController().LoginUser(
                    binding.txtEmail.text.toString(),
                    binding.txtPassword.text.toString()
                )

                var user = UserController().getLoginUser(
                    binding.txtEmail.text.toString(),
                    binding.txtPassword.text.toString()
                )

                if(binding.txtEmail.text.toString() == "" || binding.txtPassword.text.toString() == "") {

                    binding.emailField.error = getString(R.string.errorEmail)
                    binding.passwordField.error = getString(R.string.errorPassword)

                }else if (access) {

                    binding.emailField.error = null
                    binding.passwordField.error = null
                    Toast.makeText(this@LoginActivity, "Wrong e-mail or password", Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(this@LoginActivity, "Welcome", Toast.LENGTH_LONG).show()
                    iniciarSesion(user)
            }}

        }

        binding.txtForgotPassword.setOnClickListener(){
            Toast.makeText(this, "Funcion en construcci??n", Toast.LENGTH_LONG).show()
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

    fun iniciarSesion(user: User) {
        var intent = Intent(this, PrincipalActivity::class.java)
        val jsonString = Json.encodeToString(user)
        intent.putExtra("usuario", jsonString)
        startActivity(intent)
    }

}