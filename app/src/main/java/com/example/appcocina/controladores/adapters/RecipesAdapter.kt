package com.example.appcocina.controladores.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcocina.R
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.databinding.RecipesListBinding
import com.squareup.picasso.Picasso


class RecipesAdapter(val recipesList: List<Recipes>, val onClickItemSelected: (Recipes) -> Unit) :
    RecyclerView.Adapter<RecipesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return RecipesViewHolder(inflater.inflate(R.layout.recipes_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = recipesList[position]
        holder.render(item, onClickItemSelected)
    }

    override fun getItemCount(): Int = recipesList.size

}

class RecipesViewHolder(itemRecipes: View) : RecyclerView.ViewHolder(itemRecipes) {

    private val binding = RecipesListBinding.bind(itemRecipes)

    fun render(itemRecipesEntity : Recipes, onClickItemSelected: (Recipes) -> Unit){
        binding.txtNameRecipes.text = itemRecipesEntity.nombre
        val condición = itemRecipesEntity.img!!?.get(0)

        if(condición.equals('h')){
            Picasso.get().load(itemRecipesEntity.img).into(binding.imageRecipes)
        } else {
            val bitmap = BitmapFactory.decodeFile(itemRecipesEntity.img)
            binding.imageRecipes?.setImageBitmap(bitmap)
        }

        itemView.setOnClickListener {
            onClickItemSelected(itemRecipesEntity)
        }

    }
}
