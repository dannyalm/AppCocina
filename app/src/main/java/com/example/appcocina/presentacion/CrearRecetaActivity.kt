package com.example.appcocina.presentacion


import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.ActionBar
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcocina.R
import com.example.appcocina.controladores.RecipesController
import com.example.appcocina.controladores.UserController
import com.example.appcocina.controladores.adapters.IngredientsCreateAdapter
import com.example.appcocina.data.database.entidades.Recipes
import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.databinding.ActivityCrearRecetaBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class CrearRecetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearRecetaBinding
    private lateinit var addsBtn: Button
    private lateinit var recRV: RecyclerView
    private lateinit var createList:ArrayList<Recipes>
    private lateinit var createAdapter:IngredientsCreateAdapter
    private var idUsuario: Int = 0
    private var mUri: Uri? = null
    private var imgPath: String? = null
    private val OPERATION_CAPTURE_PHOTO = 1
    private val OPERATION_CHOOSE_PHOTO = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idUsuario = intent.extras?.getInt("idUsuario")!!


        binding.btnAtras.setOnClickListener(){
            onBackPressed()
        }

        binding.btnCargar.setOnClickListener {
            val checkSelfPermission = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED){
                //Solicita que se concedan permisos a esta aplicación en tiempo de ejecución
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            }
            else{
                openGallery()
            }
        }

        binding.btnCrear.setOnClickListener() {
            saveCreatedRecipe()
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
            createList.add(Recipes("","","","","","$names","$number",
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

    private fun saveCreatedRecipe(){

        var id_Recipe = UUID.randomUUID().toString()
        var nombre = binding.txtRecipeName.text.toString()
        var img = imgPath
        var pasos = binding.txtMeasures.text.toString()
        var categoria = "Beef"
        var autor = idUsuario
        var ingredientsList = getIngredientsList()

        var ingrediente1 = ingredientsList.get(0).ingrediente1.toString()
        var cantidad1 = ingredientsList.get(0).cantidad1.toString()
        var ingrediente2 = ingredientsList.get(1).ingrediente1.toString()
        var cantidad2 = ingredientsList.get(1).cantidad1.toString()
        var ingrediente3 = ingredientsList.get(2).ingrediente1.toString()
        var cantidad3 = ingredientsList.get(2).cantidad1.toString()
        var ingrediente4 = ingredientsList.get(3).ingrediente1.toString()
        var cantidad4 = ingredientsList.get(3).cantidad1.toString()
        var ingrediente5 = ingredientsList.get(4).ingrediente1.toString()
        var cantidad5 = ingredientsList.get(4).cantidad1.toString()
        var ingrediente6 = ingredientsList.get(5).ingrediente1.toString()
        var cantidad6 = ingredientsList.get(5).cantidad1.toString()
        var ingrediente7 = ingredientsList.get(6).ingrediente1.toString()
        var cantidad7 = ingredientsList.get(6).cantidad1.toString()
        var ingrediente8 = ingredientsList.get(7).ingrediente1.toString()
        var cantidad8 = ingredientsList.get(7).cantidad1.toString()
        var ingrediente9 = ingredientsList.get(8).ingrediente1.toString()
        var cantidad9 = ingredientsList.get(8).cantidad1.toString()
        var ingrediente10 = ingredientsList.get(9).ingrediente1.toString()
        var cantidad10 = ingredientsList.get(9).cantidad1.toString()
        var ingrediente11 = ingredientsList.get(10).ingrediente1.toString()
        var cantidad11 = ingredientsList.get(10).cantidad1.toString()
        var ingrediente12 = ingredientsList.get(11).ingrediente1.toString()
        var cantidad12 = ingredientsList.get(11).cantidad1.toString()
        var ingrediente13 = ingredientsList.get(12).ingrediente1.toString()
        var cantidad13 = ingredientsList.get(12).cantidad1.toString()
        var ingrediente14 = ingredientsList.get(13).ingrediente1.toString()
        var cantidad14 = ingredientsList.get(13).cantidad1.toString()
        var ingrediente15 = ingredientsList.get(14).ingrediente1.toString()
        var cantidad15 = ingredientsList.get(14).cantidad1.toString()
        var ingrediente16 = ingredientsList.get(15).ingrediente1.toString()
        var cantidad16 = ingredientsList.get(15).cantidad1.toString()
        var ingrediente17 = ingredientsList.get(16).ingrediente1.toString()
        var cantidad17 = ingredientsList.get(16).cantidad1.toString()
        var ingrediente18 = ingredientsList.get(17).ingrediente1.toString()
        var cantidad18 = ingredientsList.get(17).cantidad1.toString()
        var ingrediente19 = ingredientsList.get(18).ingrediente1.toString()
        var cantidad19 = ingredientsList.get(18).cantidad1.toString()
        var ingrediente20 = ingredientsList.get(19).ingrediente1.toString()
        var cantidad20 = ingredientsList.get(19).cantidad1.toString()

        lifecycleScope.launch(Dispatchers.Main)
        {
            val receta = Recipes(id_Recipe, nombre, img, null, null, ingrediente1, cantidad1,
                ingrediente2, cantidad2,ingrediente3, cantidad3,ingrediente4, cantidad4,ingrediente5, cantidad5,
                ingrediente6, cantidad6,ingrediente7, cantidad7,ingrediente8, cantidad8,ingrediente9, cantidad9,
                ingrediente10, cantidad10,ingrediente11, cantidad11,ingrediente12, cantidad12,ingrediente13, cantidad13,
                ingrediente14, cantidad14,ingrediente15, cantidad15,ingrediente16, cantidad16,ingrediente17, cantidad17,
                ingrediente18, cantidad18,ingrediente19, cantidad19,ingrediente20, cantidad20,pasos,0f,
                categoria, autor)
            RecipesController().saveFavRecipes(receta)
        }
        Toast.makeText(this, "Your recipe has been created successfully.", Toast.LENGTH_LONG).show()

    }








    //Para acceder a la galeria
    private fun openGallery(){
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, OPERATION_CHOOSE_PHOTO)
    }
    //Para hacer nuestra imagen como un mapa de bits
    private fun renderImage(imagePath: String?){
        if (imagePath != null) {
            imgPath=imagePath
            val bitmap = BitmapFactory.decodeFile(imagePath)
            binding.imageReceta?.setImageBitmap(bitmap)
        }
        else {
            show("ImagePath is null")
        }
    }
    //Obtenemos la ruta de la imagen
    @SuppressLint("Range")
    private fun getImagePath(uri: Uri?, selection: String?): String {
        var path: String? = null
        val cursor = contentResolver.query(uri!!, null, selection, null, null )
        if (cursor != null){
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path!!
    }
    //Manejamos nuestra imagen en Android API Nivel 19 y superior
    @TargetApi(19)
    private fun handleImageOnKitkat(data: Intent?) {
        var imagePath: String? = null
        val uri = data!!.data
        //DocumentsContract define el contrato entre un proveedor de documentos y la plataforma.
        if (DocumentsContract.isDocumentUri(this, uri)){
            val docId = DocumentsContract.getDocumentId(uri)
            if ("com.android.providers.media.documents" == uri!!.authority){
                val id = docId.split(":")[1]
                val selsetion = MediaStore.Images.Media._ID + "=" + id
                imagePath = getImagePath(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    selsetion)
            }
            else if ("com.android.providers.downloads.documents" == uri.authority){
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse(
                    "content://downloads/public_downloads"), java.lang.Long.valueOf(docId))
                imagePath = getImagePath(contentUri, null)
            }
        }
        else if ("content".equals(uri!!.scheme, ignoreCase = true)){
            imagePath = getImagePath(uri, null)
        }
        else if ("file".equals(uri!!.scheme, ignoreCase = true)){
            imagePath = uri!!.path
        }
        renderImage(imagePath)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>
                                            , grantedResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantedResults)
        when(requestCode){
            1 ->
                if (grantedResults.isNotEmpty() && grantedResults.get(0) ==
                    PackageManager.PERMISSION_GRANTED){
                    openGallery()
                }else {
                    show("Lamentablemente, se le ha denegado el permiso para realizar esta operación.")
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            OPERATION_CAPTURE_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    val bitmap = BitmapFactory.decodeStream(
                        mUri?.let { getContentResolver().openInputStream(it) })
                    binding.imageReceta!!.setImageBitmap(bitmap)
                }
            OPERATION_CHOOSE_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitkat(data)
                    }
                }
        }
    }

    //Método para capturar nuestra foto o imagen
    private fun capturePhoto(){
        val capturedImage = File(externalCacheDir, "My_Captured_Photo.jpg")
        if(capturedImage.exists()) {
            capturedImage.delete()
        }
        capturedImage.createNewFile()
        mUri = if(Build.VERSION.SDK_INT >= 24){
            FileProvider.getUriForFile(this, "info.camposha.kimagepicker.fileprovider",
                capturedImage)
        } else {
            Uri.fromFile(capturedImage)
        }

        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri)
        startActivityForResult(intent, OPERATION_CAPTURE_PHOTO)
    }

    private fun show(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    private fun getIngredientsList(): List<Recipes> {
        var ingredientsList = mutableListOf<Recipes>()
        var i = 0
        while(i<20){
            ingredientsList.add(Recipes("","","","","","","","","","","","","","","","","","","","",
                "","","","","","","","","","","","","","","","","","","","",
                "","","","","","",null,"",null))
            i++
        }
        var j = 0
        createList.forEach {
            ingredientsList.set(j, it)
            j++
        }
        return ingredientsList
    }

}

