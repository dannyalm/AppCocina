package com.example.appcocina.controladores.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcocina.R
import com.example.appcocina.databinding.IngredientsListBinding
import com.example.appcocina.data.database.entidades.Ingredients
import com.squareup.picasso.Picasso

class IngredientsAdapter(val ingredientsList: List<Ingredients>, val onClickItemSelected: (Ingredients) -> Unit) :
    RecyclerView.Adapter<IngredientsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return IngredientsViewHolder(inflater.inflate(R.layout.ingredients_list, parent, false))
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val item = ingredientsList[position]
        holder.render(item, onClickItemSelected)
    }

    override fun getItemCount(): Int = ingredientsList.size

}

class IngredientsViewHolder(itemIngredients: View) : RecyclerView.ViewHolder(itemIngredients) {

    private val binding = IngredientsListBinding.bind(itemIngredients)

    fun render(itemIngredientsEntity : Ingredients, onClickItemSelected: (Ingredients) -> Unit){
        binding.txtNameIngredients.text = itemIngredientsEntity.nombre
        Picasso.get().load(itemIngredientsEntity.img).into(binding.imageIngredients)

        itemView.setOnClickListener {
            onClickItemSelected(itemIngredientsEntity)
        }
    }
}
