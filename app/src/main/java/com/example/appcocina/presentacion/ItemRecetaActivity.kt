package com.example.appcocina.presentacion

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.appcocina.R
import com.example.appcocina.casosUso.RecipesUserUseCase
import com.example.appcocina.controladores.RecipesController
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.data.database.entidades.RecipesUserCroosRef
import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.databinding.ActivityItemRecetaBinding
import com.example.appcocina.logica.RecipesBL
import com.example.appcocina.logica.RecipesUserBL
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ItemRecetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemRecetaBinding
    private lateinit var detalle: Recipes
    private var fav: Boolean = false
    private var idUsuario: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAtras.setOnClickListener(){
            onBackPressed()
        }

        idUsuario = intent.extras?.getInt("idUsuario")!!

        var n: Recipes? = null
        intent.extras?.let {
            n = Json.decodeFromString<Recipes>(it.getString("receta").toString())
        }
        if (n != null) {
            loadRecipe(n!!)
        }

        binding.floatingActionButtonItem.setOnClickListener() {
            saveFavRecipes(n, idUsuario)
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


        binding.txtShareRecipe.setOnClickListener{
            shareRecipes()
        }

    }

    private fun saveFavRecipes(recipes: Recipes?, idUser: Int?) {
        if (recipes != null && idUser != null) {
            if (!fav) {
                var recipeUser = RecipesUserCroosRef(recipes.id_Recipes, idUser)
                lifecycleScope.launch {
                    RecipesController().saveFavRecipes(recipes)
                    RecipesUserBL().saveFavRecipesUser(recipeUser)
                    binding.floatingActionButtonItem.setImageResource(R.drawable.ic_favorite_24)
                    fav = true
                }
            } else {
                lifecycleScope.launch {
                    RecipesUserBL().deleteFavRecipesUser(recipes.id_Recipes, idUser)
                    RecipesController().deleteFavRecipes(recipes)
                    binding.floatingActionButtonItem.setImageResource(R.drawable.ic_favorite_border_12)
                    fav = false
                }
            }
        }
    }

    //Compartir receta

    private fun shareRecipes() {

        var nombreRe: String = " \n" +
                " • Recipe Name:\n"
        var ingredientesRe: String = "\n" +
                " • Ingredients: \n"
        var procedimientoRe: String = "\n" +
                " • Process: \n"

        var reciList: ArrayList<String?> = arrayListOf(
            nombreRe, detalle.nombre,
            ingredientesRe,
        )
        if ( detalle.ingrediente1 != "" && detalle.ingrediente1 != null)
            reciList.add(detalle.ingrediente1 + " - " + detalle.cantidad1)
        if (detalle.ingrediente2 != "" && detalle.ingrediente2 != null)
            reciList.add(detalle.ingrediente2 + " - " + detalle.cantidad2)
        if (detalle.ingrediente3 != "" && detalle.ingrediente3 != null)
            reciList.add(detalle.ingrediente3 + " - " + detalle.cantidad3)
        if (detalle.ingrediente4 != "" && detalle.ingrediente4 != null)
            reciList.add(detalle.ingrediente4 + " - " + detalle.cantidad4)
        if (detalle.ingrediente5 != "" && detalle.ingrediente5 != null)
            reciList.add(detalle.ingrediente5 + " - " + detalle.cantidad5)
        if (detalle.ingrediente6 != "" && detalle.ingrediente6 != null)
            reciList.add(detalle.ingrediente6 + " - " + detalle.cantidad6)
        if (detalle.ingrediente7 != "" && detalle.ingrediente7 != null)
            reciList.add(detalle.ingrediente7 + " - " + detalle.cantidad7)
        if (detalle.ingrediente8 != "" && detalle.ingrediente8 != null)
            reciList.add(detalle.ingrediente8 + " - " + detalle.cantidad8)
        if (detalle.ingrediente9 != "" && detalle.ingrediente9 != null)
            reciList.add(detalle.ingrediente9 + " - " + detalle.cantidad9)
        if (detalle.ingrediente10 != "" && detalle.ingrediente10 != null)
            reciList.add(detalle.ingrediente10 + " - " + detalle.cantidad10)
        if (detalle.ingrediente11 != "" && detalle.ingrediente11 != null)
            reciList.add(detalle.ingrediente11 + " - " + detalle.cantidad11)
        if (detalle.ingrediente12 != "" && detalle.ingrediente12 != null)
            reciList.add(detalle.ingrediente12 + " - " + detalle.cantidad12)
        if (detalle.ingrediente13 != "" && detalle.ingrediente13 != null)
            reciList.add(detalle.ingrediente13 + " - " + detalle.cantidad13)
        if (detalle.ingrediente14 != "" && detalle.ingrediente14 != null)
            reciList.add(detalle.ingrediente14 + " - " + detalle.cantidad14)
        if (detalle.ingrediente15 != "" && detalle.ingrediente15 != null)
            reciList.add(detalle.ingrediente15 + " - " + detalle.cantidad15)
        if (detalle.ingrediente16 != "" && detalle.ingrediente16 != null)
            reciList.add(detalle.ingrediente16 + " - " + detalle.cantidad16)
        if (detalle.ingrediente17 != "" && detalle.ingrediente17 != null)
            reciList.add(detalle.ingrediente17 + " - " + detalle.cantidad17)
        if (detalle.ingrediente18 != "" && detalle.ingrediente18 != null)
            reciList.add(detalle.ingrediente18 + " - " + detalle.cantidad18)
        if (detalle.ingrediente19 != "" && detalle.ingrediente19 != null)
            reciList.add(detalle.ingrediente19 + " - " + detalle.cantidad19)
        if (detalle.ingrediente20 != "" && detalle.ingrediente20 != null)
            reciList.add(detalle.ingrediente20 + " - " + detalle.cantidad20)

        reciList.add(procedimientoRe + detalle.pasos)

    /*    reciList.add(detalle.nombre.toString())
        reciList.add(detalle.pasos.toString())*/

        val sendIntent: Intent = Intent().apply {
            print(reciList)
            action = Intent.ACTION_SEND_MULTIPLE
            putStringArrayListExtra(Intent.EXTRA_TEXT,  reciList)
         // putExtra(Intent.EXTRA_TEXT, detalle.nombre)
            type = "text/*"
        }
        val shareIntent = Intent.createChooser(sendIntent, "xxxxxxx")
        startActivity(shareIntent)
    }


    private fun loadRecipe(recipeEntity: Recipes) {

        binding.progressBarDetailRecipe.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main)
        {
            detalle = RecipesController().getDetailsOneRecipe(recipeEntity.id_Recipes.toString()).get(0)
            binding.txtNombreReceta.text = detalle.nombre
            binding.txtPasos.text = detalle.pasos
            Picasso.get().load(detalle.img).into(binding.imgReceta)

            //Video
            if(detalle.video != "" && detalle.video != null){
            binding.txtYoutube.setOnClickListener() {
                val intentYoutube = Intent(Intent.ACTION_VIEW)
                intentYoutube.data = Uri.parse(detalle.video)
                startActivity(intentYoutube)
            }
            }else {
                binding.txtYoutube.setOnClickListener() {
                val toast = Toast.makeText(this@ItemRecetaActivity, "El video no está disponible", Toast.LENGTH_SHORT)
                toast.show()
            }}

            //Link
            if(detalle.direccion != "" && detalle.direccion != null){
            binding.txtSource.setOnClickListener(){
                val intentSource = Intent(Intent.ACTION_VIEW)
                intentSource.data = Uri.parse(detalle.direccion)
                startActivity(intentSource)
            }}else {
                binding.txtSource.setOnClickListener(){
                val toast = Toast.makeText(this@ItemRecetaActivity, "La dirección no está disponible", Toast.LENGTH_SHORT)
                toast.show()
            }}

            //Favoritos
            println(idUsuario)
            fav = withContext(Dispatchers.IO) { RecipesUserBL().checkIsSaved(recipeEntity.id_Recipes, idUsuario) }
            if (fav) {
                binding.floatingActionButtonItem.setImageResource(R.drawable.ic_favorite_24)
            }

            binding.progressBarDetailRecipe.visibility = View.GONE

            //Ingredientes y Cantidad
            if ( detalle.ingrediente1 != "" && detalle.ingrediente1 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente1)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad1)}
            if (detalle.ingrediente2 != "" && detalle.ingrediente2 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente2)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad2)}
            if (detalle.ingrediente3 != "" && detalle.ingrediente3 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente3)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad3)}
            if (detalle.ingrediente4 != "" && detalle.ingrediente4 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente4)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad4)}
            if (detalle.ingrediente5 != "" && detalle.ingrediente5 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente5)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad5)}
            if (detalle.ingrediente6 != "" && detalle.ingrediente6 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente6)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad6)}
            if (detalle.ingrediente7 != "" && detalle.ingrediente7 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente7)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad7)}
            if (detalle.ingrediente8 != "" && detalle.ingrediente8 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente8)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad8)}
            if (detalle.ingrediente9 != "" && detalle.ingrediente9 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente9)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad9)}
            if (detalle.ingrediente10 != "" && detalle.ingrediente10 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente10)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad10)}
            if (detalle.ingrediente11 != "" && detalle.ingrediente11 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente11)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad11)}
            if (detalle.ingrediente12 != "" && detalle.ingrediente12 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente12)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad12)}
            if (detalle.ingrediente13 != "" && detalle.ingrediente13 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente13)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad13)}
            if (detalle.ingrediente14 != "" && detalle.ingrediente14 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente14)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad14)}
            if (detalle.ingrediente15 != "" && detalle.ingrediente15 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente15)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad15)}
            if (detalle.ingrediente16 != "" && detalle.ingrediente16 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente16)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad16)}
            if (detalle.ingrediente17 != "" && detalle.ingrediente17 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente17)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad17)}
            if (detalle.ingrediente18 != "" && detalle.ingrediente18 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente18)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad18)}
            if (detalle.ingrediente19 != "" && detalle.ingrediente19 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente19)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad19)}
            if (detalle.ingrediente20 != "" && detalle.ingrediente20 != null){
                binding.txtIngredientes.append("\n \u2022"+detalle.ingrediente20)
            binding.txtCantidad.append("\n \u2022"+detalle.cantidad20)}
        }
    }
}