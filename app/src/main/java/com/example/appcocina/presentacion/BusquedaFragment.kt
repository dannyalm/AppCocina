package com.example.appcocina.presentacion

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcocina.controladores.IngredientsController
import com.example.appcocina.controladores.adapters.IngredientsAdapter
import com.example.appcocina.controladores.adapters.RecipesAdapter
import com.example.appcocina.data.database.entidades.Ingredients
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.databinding.FragmentBusquedaBinding
import kotlinx.coroutines.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class BusquedaFragment : Fragment() {

    private lateinit var binding : FragmentBusquedaBinding
    private var job: Job? = null
    private var items = ArrayList<Ingredients>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusquedaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    var itemsFiltered = items.filter {
                        it.nombre.toString().contains(query)
                    }
                    binding.ingredientsListRV.adapter =
                        IngredientsAdapter(itemsFiltered) { getIngredientsItem(it) }
                    binding.ingredientsListRV.layoutManager =
                        LinearLayoutManager(binding.ingredientsListRV.context)
                    println(items)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = true

        })

        binding.searchView.setOnCloseListener {
            onStart()
            binding.searchView.visibility = View.GONE
            false
        }

        binding.floatingActionButton.setOnClickListener() {
            val visible = binding.searchView.visibility

            if (visible == View.GONE) {
                binding.searchView.visibility = View.VISIBLE
                binding.searchView.setQuery("", true);
                binding.searchView.isFocusable = true;
                binding.searchView.isIconified = false;
                binding.searchView.requestFocusFromTouch();
            }
        }



    }

    override fun onStart() {
        super.onStart()
        loadIngredients()

        binding.swipeRefreshIngredients.setOnRefreshListener {
            loadIngredients()
            binding.swipeRefreshIngredients.isRefreshing = false
        }

    }

    fun loadIngredients() {
        binding.ingredientsListRV.clearAnimation()
        binding.progressBarIngredients.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            items = withContext(Dispatchers.IO) {
                IngredientsController().getIngredients()
            } as ArrayList<Ingredients>
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