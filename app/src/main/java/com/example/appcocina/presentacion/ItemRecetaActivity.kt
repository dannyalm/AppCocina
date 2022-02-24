package com.example.appcocina.presentacion

import android.content.Intent
import android.icu.text.DisplayContext
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.appcocina.R
import com.example.appcocina.controladores.RecipesController
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.databinding.ActivityItemRecetaBinding
import com.example.appcocina.logica.RecipesBL
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ItemRecetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemRecetaBinding
    private lateinit var detalle: Recipes
    private var fav: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAtras.setOnClickListener(){
            onBackPressed()
        }

        var n: Recipes? = null
        intent.extras?.let {
            n = Json.decodeFromString<Recipes>(it.getString("receta").toString())
        }
        if (n != null) {
            loadRecipe(n!!)
        }

        binding.floatingActionButtonItem.setOnClickListener() {
            saveFavRecipes(n)
        }

        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

            if (rating == 1f){
                binding.txtRating.setText("Bad")
            }else if (rating == 2f){
                binding.txtRating.setText("Neither good nor bad")
            }else if (rating == 3f){
                binding.txtRating.setText("Good")
            }else if (rating == 4f){
                binding.txtRating.setText("Delicius")
            }else{
                binding.txtRating.setText("I love it")
            }

        }

    }



    private fun loadRecipe(recipeEntity: Recipes) {

        binding.progressBarDetailRecipe.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main)
        {
            detalle = RecipesController().getDetailsOneRecipe(recipeEntity.id.toString()).get(0)
            binding.txtNombreReceta.text = detalle.nombre
            binding.txtPasos.text = detalle.pasos
            Picasso.get().load(detalle.img).into(binding.imgReceta)

            //Video
            binding.txtYoutube.setOnClickListener() {
                val intentYoutube = Intent(Intent.ACTION_VIEW)
                intentYoutube.data = Uri.parse(detalle.video)
                startActivity(intentYoutube)
            }

            //Link
            binding.txtSource.setOnClickListener(){
                val intentSource = Intent(Intent.ACTION_VIEW)
                intentSource.data = Uri.parse(detalle.direccion)
                startActivity(intentSource)
            }


            fav = withContext(Dispatchers.IO) { RecipesBL().checkIsSaved(recipeEntity.id) }
            if (fav) {
                binding.floatingActionButtonItem.setImageResource(R.drawable.ic_favorite_24)
            }

            binding.progressBarDetailRecipe.visibility = View.GONE
        }

    }

    private fun saveFavRecipes(recipes: Recipes?) {
        if (recipes != null) {
            if (!fav) {
                lifecycleScope.launch {
                    RecipesController().saveFavRecipes(recipes)
                    binding.floatingActionButtonItem.setImageResource(R.drawable.ic_favorite_24)
                    fav = true
                }
            } else {
                lifecycleScope.launch {
                    RecipesController().deleteFavRecipes(recipes)
                    binding.floatingActionButtonItem.setImageResource(R.drawable.ic_favorite_border_12)
                    fav = false
                }
            }
        }
    }

}