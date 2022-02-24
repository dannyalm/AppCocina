package com.example.appcocina.presentacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcocina.controladores.RecipesController
import com.example.appcocina.controladores.adapters.RecipesAdapter
import com.example.appcocina.data.database.entidades.Ingredients
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.databinding.ActivityFilterIngredientBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FilterIngredientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilterIngredientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterIngredientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAtras.setOnClickListener(){
            onBackPressed()
        }

        var n: Ingredients? = null
        intent.extras?.let {
            n = Json.decodeFromString<Ingredients>(it.getString("ingrediente").toString())
        }
        if (n != null) {
            loadRecipes(n!!)
        }
    }

    fun loadRecipes(ingredientsEntity: Ingredients) {
        binding.txtNameIngredient.text = ingredientsEntity.nombre
        binding.txtDescripcionIngrediente.text = ingredientsEntity.descripcion
        Picasso.get().load(ingredientsEntity.img).into(binding.imgIngrediente)

        binding.recipesListRV.clearAnimation()
        binding.progressBarRecipes.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.Main)
        {
            val items = withContext(Dispatchers.IO) {
                RecipesController().getRecipesByIngredient(ingredientsEntity.nombre.toString())
            }
            binding.recipesListRV.layoutManager =
                LinearLayoutManager(binding.recipesListRV.context)
            binding.recipesListRV.adapter = RecipesAdapter(items) { getRecipesItem(it) }
            binding.progressBarRecipes.visibility = View.GONE

        }
    }
    private fun getRecipesItem(recipesEntity: Recipes) {
        var i = Intent(this, ItemRecetaActivity::class.java)
        val jsonString = Json.encodeToString(recipesEntity)
        i.putExtra("receta", jsonString)
        startActivity(i)
    }


}