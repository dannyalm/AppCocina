package com.example.appcocina.controladores.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.appcocina.R
import com.example.appcocina.data.database.entidades.Recipes

class IngredientsCreateAdapter(val c:Context,val createList:ArrayList<Recipes>):RecyclerView.Adapter<IngredientsCreateAdapter.UserViewHolder>()
{

    inner class UserViewHolder(val v:View):RecyclerView.ViewHolder(v){
        var ingredientName:TextView
        var ingredientAmount:TextView
        var mMenus:ImageView

        init {
            ingredientName = v.findViewById<TextView>(R.id.nIngredient)
            ingredientAmount = v.findViewById<TextView>(R.id.cIngredient)
            mMenus = v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener { popupMenus(it) }
        }

        private fun popupMenus(v:View) {
            val position = createList[adapterPosition]
            val popupMenus = PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editText->{
                        val v = LayoutInflater.from(c).inflate(R.layout.add_ingredient_item,null)
                        val name = v.findViewById<EditText>(R.id.ingredientName)
                        val number = v.findViewById<EditText>(R.id.ingredientNo)
                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Ok"){
                                    dialog,_->
                               position.ingrediente1 = name.text.toString()
                               position.cantidad1 = number.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(c,"Ingredient is Edited",Toast.LENGTH_SHORT).show()
                                dialog.dismiss()

                            }
                            .setNegativeButton("Cancel"){
                                    dialog,_->
                                dialog.dismiss()

                            }
                            .create()
                            .show()

                        true
                    }
                    R.id.delete->{
                        /**set delete*/
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning_24)
                            .setMessage("Are you sure delete this Ingredient")
                            .setPositiveButton("Yes"){
                                    dialog,_->
                                createList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c,"Deleted this Ingredient",Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                    dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else-> true
                }

            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v  = inflater.inflate(R.layout.list_item_ingredients,parent,false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = createList[position]
        holder.ingredientName.text = newList.ingrediente1
        holder.ingredientAmount.text = newList.cantidad1
    }

    override fun getItemCount(): Int {
        return  createList.size
    }
}

