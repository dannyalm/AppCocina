package com.example.appcocina.presentacion

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcocina.controladores.CategoriesController
import com.example.appcocina.controladores.adapters.CategoriesAdapter
import com.example.appcocina.data.database.entidades.Categories
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.databinding.FragmentHomeBinding
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    var us: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

  override fun onStart() {
      super.onStart()

      binding.createButton.setOnClickListener() {
          crearReceta(us!!.id_User)
      }
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.intent.extras?.let {
            us = Json.decodeFromString<User>(it.getString("usuario").toString())
        }

        loadCategories(us!!.id_User)

        binding.swipeRefreshCategories.setOnRefreshListener {
            loadCategories(us!!.id_User)
            binding.swipeRefreshCategories.isRefreshing = false
        }
    }

    fun loadCategories(idUs: Int) {

        binding.categoriesListRV.clearAnimation()
        binding.progressBarCategories.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.Main)
        {

            val items = withContext(Dispatchers.IO) {
                CategoriesController().getCategories()
            }
            binding.categoriesListRV.layoutManager =
                LinearLayoutManager(binding.categoriesListRV.context)
            binding.categoriesListRV.adapter = CategoriesAdapter(items) { getCategoriesItem(it, idUs) }
            binding.progressBarCategories.visibility = View.GONE

        }
    }

    private fun getCategoriesItem(categoriesEntity: Categories, idUser: Int) {
        var i = Intent(activity, FilterCategoryActivity::class.java)
        val jsonString = Json.encodeToString(categoriesEntity)
        i.putExtra("categoria", jsonString)
        i.putExtra("idUsuario", idUser)
        startActivity(i)
    }

    fun crearReceta(idUser: Int) {
        var intent = Intent(activity, CrearRecetaActivity::class.java)
        intent.putExtra("idUsuario", idUser)
        startActivity(intent)
    }

}