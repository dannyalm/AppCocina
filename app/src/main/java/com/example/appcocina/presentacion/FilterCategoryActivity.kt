package com.example.appcocina.presentacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcocina.controladores.RecipesController
import com.example.appcocina.controladores.adapters.RecipesAdapter
import com.example.appcocina.data.database.entidades.Categories
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.databinding.ActivityFilterCategoryBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FilterCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilterCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAtras.setOnClickListener(){
            onBackPressed()
        }

        var idUsuario = intent.extras?.getInt("idUsuario")

        var n: Categories? = null
        intent.extras?.let {
            n = Json.decodeFromString<Categories>(it.getString("categoria").toString())
        }
        if (n != null) {
            loadRecipes(n!!, idUsuario!!)
        }


    }

    fun loadRecipes(categoriesEntity: Categories, idUs: Int) {
        binding.txtNameCategory.text = categoriesEntity.nombre
        binding.txtDescripcion.text = categoriesEntity.descripcion
        Picasso.get().load(categoriesEntity.img).into(binding.imgCategoria)

        binding.recipesListRV.clearAnimation()
        binding.progressBarRecipes.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.Main)
        {
            val items = withContext(Dispatchers.IO) {
                RecipesController().getRecipesByCategory(categoriesEntity.nombre.toString())
            }
            binding.recipesListRV.layoutManager =
                LinearLayoutManager(binding.recipesListRV.context)
            binding.recipesListRV.adapter = RecipesAdapter(items) { getRecipesItem(it, idUs) }
            binding.progressBarRecipes.visibility = View.GONE

        }
    }

    private fun getRecipesItem(recipesEntity: Recipes, idUser: Int) {
        var i = Intent(this, ItemRecetaActivity::class.java)
        val jsonString = Json.encodeToString(recipesEntity)
        i.putExtra("receta", jsonString)
        i.putExtra("idUsuario", idUser)
        startActivity(i)
    }


}