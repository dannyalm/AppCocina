package com.example.appcocina.presentacion

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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