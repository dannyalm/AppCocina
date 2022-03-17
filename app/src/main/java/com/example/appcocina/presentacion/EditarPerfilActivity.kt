package com.example.appcocina.presentacion

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
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
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.appcocina.R
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.appcocina.data.database.entidades.User
import com.example.appcocina.databinding.ActivityEditarPerfilBinding
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.appcocina.controladores.UserController
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import androidx.core.content.FileProvider
import java.io.File



class EditarPerfilActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityEditarPerfilBinding
    private var mUri: Uri? = null
    private var imgPath: String? = null
    private val OPERATION_CAPTURE_PHOTO = 1
    private val OPERATION_CHOOSE_PHOTO = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var us: User? = null
        intent.extras?.let {
            us = Json.decodeFromString<User>(it.getString("usuario").toString())
        }
        if (us != null) {
            loadInformation(us!!)
        }

        binding.btnAtras.setOnClickListener(){
            onBackPressed()
        }

        //Recuperamos los elementos del string array
        val lista_generos = resources.getStringArray(R.array.generos)

        //Creación del adapter
        val adapter = ArrayAdapter(
            this, // Contexto
            R.layout.list_item_generos, //Layout del diseño
            lista_generos //Array
        )
        //Agregamos el adapter al autoCompleteTextView
        with(binding.txtGenero) {
            setAdapter(adapter)
            onItemClickListener = this@EditarPerfilActivity
        }

        binding.btnEditar.setOnClickListener {
            editUser(us!!)
            var intent = Intent(this, PrincipalActivity::class.java)
            val jsonString = Json.encodeToString(us)
            intent.putExtra("usuario", jsonString)
            startActivity(intent)
        }

//        binding.btn1.setOnClickListener {
//            capturePhoto()
//        }

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

        binding.editProfile.setOnClickListener() {
            hideSoftKeyboard(binding.root)
        }


    }

    fun loadInformation(user: User) {
        binding.txtUsuario.text = Editable.Factory.getInstance().newEditable(user.nombre)
        binding.txtApellido.text = Editable.Factory.getInstance().newEditable(user.apellido)
        binding.txtEmail.text = Editable.Factory.getInstance().newEditable(user.correo)
        binding.txtPassword.text = Editable.Factory.getInstance().newEditable(user.contrasena)
        binding.txtEdad.text = Editable.Factory.getInstance().newEditable(user.edad.toString())
        binding.txtGenero.text = Editable.Factory.getInstance().newEditable(user.sexo)
        renderImage(user.img)
    }

    fun editUser(user: User) {
        if (user != null) {
            user.nombre = binding.txtUsuario.text.toString()
            user.apellido = binding.txtApellido.text.toString()
            user.correo = binding.txtEmail.text.toString()
            user.contrasena = binding.txtPassword.text.toString()
            user.edad = binding.txtEdad.text.toString().toInt()
            user.sexo = binding.txtGenero.text.toString()
            user.img = imgPath

            lifecycleScope.launch {
                UserController().updateUser(user)
            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //Recuperamos el item seleccionado por su posición
        val item = parent?.getItemAtPosition(position).toString()
        //Mostramos en un toast del elemento seleccionado
        Toast.makeText(this@EditarPerfilActivity,item,Toast.LENGTH_SHORT).show()
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
            binding.imagePerfil?.setImageBitmap(bitmap)
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
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    selsetion)
            }
            else if ("com.android.providers.downloads.documents" == uri.authority){
                val contentUri = ContentUris.withAppendedId(Uri.parse(
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
                    binding.imagePerfil!!.setImageBitmap(bitmap)
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

    fun hideSoftKeyboard(view: View) {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}