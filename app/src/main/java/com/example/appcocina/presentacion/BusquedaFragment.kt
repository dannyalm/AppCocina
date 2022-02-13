package com.example.appcocina.presentacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcocina.controladores.adapters.IngredientsAdapter
import com.example.appcocina.databinding.FragmentBusquedaBinding
import com.example.appcocina.logica.IngredientsBL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BusquedaFragment : Fragment() {

    private lateinit var binding : FragmentBusquedaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusquedaBinding.inflate(inflater, container, false)

        loadIngredients()
        return binding.root
    }

    fun loadIngredients() {
        binding.ingredientsListRV.clearAnimation()
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.Main)
        {
            val lstIngredients = IngredientsBL().getIngredientsList()
            binding.ingredientsListRV.adapter = IngredientsAdapter(lstIngredients)
            binding.ingredientsListRV.layoutManager = LinearLayoutManager(binding.ingredientsListRV.context)
            binding.progressBar.visibility = View.GONE
        }
    }

}