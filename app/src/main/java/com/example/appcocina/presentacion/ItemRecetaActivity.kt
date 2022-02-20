package com.example.appcocina.presentacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.appcocina.R
import com.example.appcocina.data.database.entidades.Categories
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.databinding.ActivityItemRecetaBinding
import com.example.appcocina.databinding.ActivityLoginBinding
import com.example.appcocina.logica.RecipesBL
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ItemRecetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemRecetaBinding
    private lateinit var detalle: Recipes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener(){
            onBackPressed()
        }

        var n: Recipes? = null
        intent.extras?.let {
            n = Json.decodeFromString<Recipes>(it.getString("receta").toString())
        }
        if (n != null) {
            loadRecipe(n!!)
        }
    }
    private fun loadRecipe(recipeEntity: Recipes) {

        binding.progressBarDetailRecipe.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main)
        {
            detalle = RecipesBL().getRecipe(recipeEntity.id.toString()).get(0)
            binding.txtNombreReceta.text = detalle.nombre
            binding.txtIngredientes.text = detalle.ingre
            binding.txtPasos.text = detalle.pasos
            Picasso.get().load(detalle.img).into(binding.imgReceta)
            binding.progressBarDetailRecipe.visibility = View.GONE
        }
    }

}