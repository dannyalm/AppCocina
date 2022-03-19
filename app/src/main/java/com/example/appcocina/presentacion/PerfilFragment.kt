package com.example.appcocina.presentacion

import android.R
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.appcocina.controladores.adapters.ViewPagerAdapter
import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.databinding.FragmentPerfilBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class PerfilFragment : Fragment() {

    private lateinit var binding : FragmentPerfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        var us: User? = null
        activity!!.intent.extras?.let {
            us = Json.decodeFromString<User>(it.getString("usuario").toString())
        }

        if (us != null) {
            loadInformation(us!!)
        }

        binding.btnEdit.setOnClickListener(){
            editarPerfil(us!!)
        }

        binding.btnLogout.setOnClickListener(){
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent)
        }

        val adapter= ViewPagerAdapter(childFragmentManager,lifecycle)

        binding.viewPager2.adapter=adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="Saved Recipes"
                }
                1->{
                    tab.text="My Recipes"
                }
            }
        }.attach()


    }

    fun loadInformation(user: User) {
        binding.textNombre.text = user.nombre
        binding.textApellido.text = user.apellido
       // binding.txtCorreo.text = user.correo
        if (user.img!=null){
            val bitmap = BitmapFactory.decodeFile(user.img)
            binding.imgPerfil?.setImageBitmap(bitmap)
        }
    }

    fun editarPerfil(user: User) {
        val intent = Intent(activity, EditarPerfilActivity::class.java)
        val jsonString = Json.encodeToString(user)
        intent.putExtra("usuario", jsonString)
        startActivity(intent)
    }

}