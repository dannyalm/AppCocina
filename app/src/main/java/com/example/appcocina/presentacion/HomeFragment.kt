package com.example.appcocina.presentacion

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcocina.controladores.CategoriesController
import com.example.appcocina.controladores.adapters.CategoriesAdapter
import com.example.appcocina.data.database.entidades.Categories
import com.example.appcocina.databinding.FragmentHomeBinding
import kotlinx.coroutines.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadCategories()

        binding.swipeRefreshCategories.setOnRefreshListener {
            loadCategories()
            binding.swipeRefreshCategories.isRefreshing = false
        }
    }

    fun loadCategories() {

        binding.categoriesListRV.clearAnimation()
        binding.progressBarCategories.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.Main)
        {

            val items = withContext(Dispatchers.IO) {
                CategoriesController().getCategories()
            }
            binding.categoriesListRV.layoutManager =
                LinearLayoutManager(binding.categoriesListRV.context)
            binding.categoriesListRV.adapter = CategoriesAdapter(items) { getCategoriesItem(it) }
            binding.progressBarCategories.visibility = View.GONE

        }
    }

    private fun getCategoriesItem(categoriesEntity: Categories) {
        var i = Intent(activity, FilterCategoryActivity::class.java)
        val jsonString = Json.encodeToString(categoriesEntity)
        i.putExtra("categoria", jsonString)
        startActivity(i)
    }


}