package com.example.appcocina.controladores.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcocina.R
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.databinding.RecipesListBinding
import com.squareup.picasso.Picasso


class RecipesAdapter(val recipesList: List<Recipes>) : RecyclerView.Adapter<RecipesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return RecipesViewHolder(inflater.inflate(R.layout.recipes_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = recipesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = recipesList.size

}

class RecipesViewHolder(recipesView: View) : RecyclerView.ViewHolder(recipesView) {

    val binding = RecipesListBinding.bind(recipesView)

    fun render(recipesView : Recipes){
        binding.txtNameRecipes.text = recipesView.nombre
        Picasso.get().load(recipesView.img).into(binding.imageRecipes)

    }
}
