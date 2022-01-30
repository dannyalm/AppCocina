package com.example.appcocina.presentacion

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.appcocina.R
import com.example.appcocina.databinding.ActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPrincipalBinding
    private var lstFragments = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())
        lstFragments.add(R.id.itHome)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.itHome -> {
                    loadFragment(HomeFragment())
                    lstFragments.add(R.id.itHome)
                    true
                }
                R.id.itBusqueda -> {
                    loadFragment(BusquedaFragment())
                    lstFragments.add(R.id.itBusqueda)
                    true
                }
                R.id.itCrearReceta -> {
                    loadFragment(CrearRecetaFragment())
                    lstFragments.add(R.id.itCrearReceta)
                    true
                }
                R.id.itPerfil -> {
                    loadFragment(PerfilFragment())
                    lstFragments.add(R.id.itPerfil)
                    true
                }
                else -> false
            }
        }


        binding.homePrincipal.setOnClickListener() {
            hideSoftKeyboard(binding.root)
        }
    }

    fun hideSoftKeyboard(view: View) {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun loadFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.FrameLayout.id, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        lstFragments.removeLast()
        if (lstFragments.isNotEmpty()) {
            binding.bottomNavigation.menu.findItem(lstFragments.last()).setChecked(true)
        }
    }
}