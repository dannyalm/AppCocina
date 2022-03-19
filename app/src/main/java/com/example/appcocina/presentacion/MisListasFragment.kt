package com.example.appcocina.presentacion

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcocina.controladores.RecipesUserController
import com.example.appcocina.controladores.adapters.RecipesAdapter
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.databinding.FragmentMisListasBinding
import com.example.appcocina.logica.RecipesUserBL
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json




class MisListasFragment : Fragment() {

    private lateinit var binding : FragmentMisListasBinding
    private var items = ArrayList<Recipes>()
    private var idUsuario: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMisListasBinding.inflate(inflater, container, false)
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
                    binding.listRecyclerViewFav.adapter =
                        RecipesAdapter(itemsFiltered) { getNewsItem(it, idUsuario) }
                    binding.listRecyclerViewFav.layoutManager =
                        LinearLayoutManager(binding.listRecyclerViewFav.context)
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

        var us: User? = null
        activity!!.intent.extras?.let {
            us = Json.decodeFromString<User>(it.getString("usuario").toString())
        }

        idUsuario = us!!.id_User

        binding.progressBarFav.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            items = withContext(Dispatchers.IO) {
                RecipesUserController().getFavRecipesUser(us!!.id_User)
            } as ArrayList<Recipes>
            binding.listRecyclerViewFav.adapter =
                RecipesAdapter(items) { getNewsItem(it, idUsuario) }
            binding.listRecyclerViewFav.layoutManager =
                LinearLayoutManager(binding.listRecyclerViewFav.context)
            binding.progressBarFav.visibility = View.INVISIBLE
        }
    }

    private fun getNewsItem(newsEntity: Recipes, idUser: Int) {
        val i = Intent(activity, ItemRecetaActivity::class.java)
        val jsonString = Json.encodeToString(newsEntity)
        i.putExtra("receta", jsonString)
        i.putExtra("idUsuario", idUser)
        startActivity(i)
    }

}