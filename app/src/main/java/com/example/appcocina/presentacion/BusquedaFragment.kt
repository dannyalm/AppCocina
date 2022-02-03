package com.example.appcocina.presentacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcocina.controladores.adapters.IngredientsAdapter
import com.example.appcocina.databinding.FragmentBusquedaBinding
import com.example.appcocina.logica.IngredientsBL

class BusquedaFragment : Fragment() {

    private lateinit var binding : FragmentBusquedaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusquedaBinding.inflate(inflater, container, false)

        val lstIngredients = IngredientsBL().getIngredientsList()
        binding.IngredientsListRV.adapter = IngredientsAdapter(lstIngredients)
        binding.IngredientsListRV.layoutManager = LinearLayoutManager(binding.IngredientsListRV.context)
        return binding.root
    }

}