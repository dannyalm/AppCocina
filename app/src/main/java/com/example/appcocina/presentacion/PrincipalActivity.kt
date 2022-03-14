package com.example.appcocina.presentacion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.appcocina.R
import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.databinding.ActivityPrincipalBinding
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPrincipalBinding
    private var lstFragments = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var us: User? = null
        intent.extras?.let {
            us = Json.decodeFromString<User>(it.getString("usuario").toString())
        }
        if (us != null) {
            passUser(us!!)
        }

        changeFragment(R.id.itHome,HomeFragment())
        lstFragments.add(R.id.itHome)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.itHome -> {
                    changeFragment(R.id.itHome,HomeFragment())
                    lstFragments.add(R.id.itHome)
                    true
                }
                R.id.itBusqueda -> {
                    changeFragment(R.id.itBusqueda,BusquedaFragment())
                    lstFragments.add(R.id.itBusqueda)
                    true
                }
                R.id.itCrearReceta -> {
                    changeFragment(R.id.itCrearReceta,CrearRecetaFragment())
                    lstFragments.add(R.id.itCrearReceta)
                    true
                }
                R.id.itPerfil -> {
                    changeFragment(R.id.itPerfil,PerfilFragment())
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

    private fun changeFragment(tagToChange: Int, fragment: Fragment? = null) {
        var addStack: Boolean = false
        val ft = supportFragmentManager.beginTransaction()

        if (lstFragments.isNotEmpty()) {
            val currentFragment =
                supportFragmentManager.findFragmentByTag(lstFragments.last().toString())!!
            val toChangeFragment = supportFragmentManager.findFragmentByTag(tagToChange.toString())
            currentFragment.onPause()

            if (toChangeFragment != null) {
                if (currentFragment != toChangeFragment) {
                    addStack = true
                    ft.setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out
                    );
                    ft.hide(currentFragment).show(toChangeFragment)
                    toChangeFragment.onResume()
                }
            } else {
                if (fragment != null) {
                    addStack = true
                    ft.setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out
                    );
                    ft.hide(currentFragment)
                        .add(binding.FrameLayout.id, fragment, tagToChange.toString())
                }
            }
        } else {
            if (fragment != null) {
                ft.add(binding.FrameLayout.id, fragment, tagToChange.toString())
                addStack = true
            }
        }

        if (addStack) {
            ft.commit()
            ft.addToBackStack(tagToChange.toString())
            lstFragments.add(tagToChange)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (lstFragments.size > 1) {
            lstFragments.removeLast()
            binding.bottomNavigation.menu.findItem(lstFragments.last()).isChecked = true
        }
    }

    fun passUser(user: User) {
        var intent = Intent(this, PerfilFragment::class.java)
        val jsonString = Json.encodeToString(user)
        intent.putExtra("usuario", jsonString)
    }
}