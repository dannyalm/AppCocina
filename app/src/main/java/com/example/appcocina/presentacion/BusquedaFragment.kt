package com.example.appcocina.presentacion

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcocina.controladores.adapters.CategoriesAdapter
import com.example.appcocina.controladores.adapters.IngredientsAdapter
import com.example.appcocina.data.database.entidades.Categories
import com.example.appcocina.data.database.entidades.Ingredients
import com.example.appcocina.databinding.FragmentBusquedaBinding
import com.example.appcocina.logica.CategoriesBL
import com.example.appcocina.logica.IngredientsBL
import kotlinx.coroutines.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class BusquedaFragment : Fragment() {

    private lateinit var binding : FragmentBusquedaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusquedaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadIngredients()

        binding.swipeRefreshIngredients.setOnRefreshListener {
            loadIngredients()
        binding.swipeRefreshIngredients.isRefreshing = false
        }

    }

    fun loadIngredients() {
        binding.ingredientsListRV.clearAnimation()
        binding.progressBarIngredients.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.Main)
        {
            val items = withContext(Dispatchers.IO) {
                IngredientsBL().getIngredientsList()
            }
            binding.ingredientsListRV.layoutManager =
                LinearLayoutManager(binding.ingredientsListRV.context)
            binding.ingredientsListRV.adapter = IngredientsAdapter(items) { getIngredientsItem(it) }
            binding.progressBarIngredients.visibility = View.GONE
        }
    }

    private fun getIngredientsItem(ingredientsEntity: Ingredients) {
        var i = Intent(activity, FilterIngredientActivity::class.java)
        val jsonString = Json.encodeToString(ingredientsEntity)
        i.putExtra("ingrediente", jsonString)
        startActivity(i)
    }

}