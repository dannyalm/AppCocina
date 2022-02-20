package com.example.appcocina.controladores.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcocina.R
import com.example.appcocina.data.database.entidades.Categories
import com.example.appcocina.databinding.CategoriesListBinding
import com.squareup.picasso.Picasso


class CategoriesAdapter(val categoriesList: List<Categories>, val onClickItemSelected: (Categories) -> Unit) :
    RecyclerView.Adapter<CategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoriesViewHolder(inflater.inflate(R.layout.categories_list, parent, false))
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = categoriesList[position]
        holder.render(item, onClickItemSelected)
    }

    override fun getItemCount(): Int = categoriesList.size

}

class CategoriesViewHolder(itemCategories: View) : RecyclerView.ViewHolder(itemCategories) {

    private val binding = CategoriesListBinding.bind(itemCategories)

    fun render(itemCategoriesEntity : Categories, onClickItemSelected: (Categories) -> Unit){
        binding.txtNameCategories.text = itemCategoriesEntity.nombre
        Picasso.get().load(itemCategoriesEntity.img).into(binding.imageCategories)

        itemView.setOnClickListener {
            onClickItemSelected(itemCategoriesEntity)
        }

    }
}
