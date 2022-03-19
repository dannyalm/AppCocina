package com.example.appcocina.presentacion


import android.app.ActionBar
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcocina.R
import com.example.appcocina.controladores.adapters.IngredientsCreateAdapter
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.databinding.ActivityCrearRecetaBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CrearRecetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearRecetaBinding
    private lateinit var addsBtn: Button
    private lateinit var recRV: RecyclerView
    private lateinit var createList:ArrayList<Recipes>
    private lateinit var createAdapter:IngredientsCreateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAtras.setOnClickListener(){
            onBackPressed()
        }
        binding.createRecipe.setOnClickListener() {
            hideSoftKeyboard(binding.root)
        }

        /**set List*/
        createList = ArrayList()
        /**set find Id*/
        addsBtn = findViewById(R.id.addingBtn)
        recRV = findViewById(R.id.crearRV)
        /**set Adapter*/
        createAdapter = IngredientsCreateAdapter(this,createList)
        /**setRecycler view Adapter*/
        recRV.layoutManager = LinearLayoutManager(this)
        recRV.adapter = createAdapter
        /**set Dialog*/
        addsBtn.setOnClickListener { addInfo() }

    }

    fun hideSoftKeyboard(view: View) {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun addInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_ingredient_item,null)
        /**set view*/
        val ingredientName = v.findViewById<EditText>(R.id.ingredientName)
        val ingredientNo = v.findViewById<EditText>(R.id.ingredientNo)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            val names = ingredientName.text.toString()
            val number = ingredientNo.text.toString()
            createList.add(Recipes("","","","","","Name: $names","Mobile No. : $number",
            "","","","","","","","","","",""
                ,"","","","","","","","","",""
                ,"","","","","","","","",""
                ,"","","","","","","","","",null,"",null))
            createAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding User Information Success",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()
    }
    /**ok now run this */

}

