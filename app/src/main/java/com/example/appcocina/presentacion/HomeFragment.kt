package com.example.appcocina.presentacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcocina.controladores.adapters.CategoriesAdapter
import com.example.appcocina.controladores.adapters.IngredientsAdapter
import com.example.appcocina.controladores.adapters.RecipesAdapter
import com.example.appcocina.databinding.FragmentHomeBinding
import com.example.appcocina.logica.CategoriesBL
import com.example.appcocina.logica.IngredientsBL
import com.example.appcocina.logica.RecipesBL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        loadRecipes()
        loadCategories()
        return binding.root
    }

    fun loadCategories() {
        binding.categoriesListRV.clearAnimation()
        binding.progressBarCategories.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.Main)
        {
            val lstCategories = CategoriesBL().getCategoriesList()
            binding.categoriesListRV.adapter = CategoriesAdapter(lstCategories)
            binding.categoriesListRV.layoutManager = LinearLayoutManager(binding.categoriesListRV.context)
            binding.progressBarCategories.visibility = View.GONE
        }
    }

    fun loadRecipes() {
        binding.recipesListRV.clearAnimation()
        binding.progressBarRecipes.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.Main)
        {
            val lstRecipes = RecipesBL().getRecipesList()
            binding.recipesListRV.adapter = RecipesAdapter(lstRecipes)
            binding.recipesListRV.layoutManager = LinearLayoutManager(binding.recipesListRV.context)
            binding.progressBarRecipes.visibility = View.GONE
        }
    }

}