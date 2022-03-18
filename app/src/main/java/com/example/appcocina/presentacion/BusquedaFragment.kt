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
import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.databinding.FragmentBusquedaBinding
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class BusquedaFragment : Fragment() {

    private lateinit var binding : FragmentBusquedaBinding
    private var items = ArrayList<Ingredients>()
    var us: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusquedaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.intent.extras?.let {
            us = Json.decodeFromString<User>(it.getString("usuario").toString())
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    var itemsFiltered = items.filter {
                        it.nombre.toString().contains(query)
                    }
                    binding.ingredientsListRV.adapter =
                        IngredientsAdapter(itemsFiltered) { getIngredientsItem(it, us!!.id_User) }
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
        loadIngredients(us!!.id_User)
        binding.swipeRefreshIngredients.setOnRefreshListener {
            loadIngredients(us!!.id_User)
            binding.swipeRefreshIngredients.isRefreshing = false
        }

    }

    fun loadIngredients(idUs: Int) {
        binding.ingredientsListRV.clearAnimation()
        binding.progressBarIngredients.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            items = withContext(Dispatchers.IO) {
                IngredientsController().getIngredients()
            } as ArrayList<Ingredients>
            binding.ingredientsListRV.layoutManager =
                LinearLayoutManager(binding.ingredientsListRV.context)
            binding.ingredientsListRV.adapter = IngredientsAdapter(items) { getIngredientsItem(it, idUs) }
            binding.progressBarIngredients.visibility = View.GONE
        }
    }

    private fun getIngredientsItem(ingredientsEntity: Ingredients, idUser: Int) {
        var i = Intent(activity, FilterIngredientActivity::class.java)
        val jsonString = Json.encodeToString(ingredientsEntity)
        i.putExtra("ingrediente", jsonString)
        i.putExtra("idUsuario", idUser)
        startActivity(i)
    }

}