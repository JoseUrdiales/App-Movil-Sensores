package com.example.tallerform

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Camara : AppCompatActivity() {

    private var photoUri: Uri? = null
    private lateinit var imageView: ImageView


    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val cameraGranted = permissions[Manifest.permission.CAMERA] ?: false
        val storageGranted = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] ?: false
        } else {
            true
        }

        if (cameraGranted && storageGranted) {
            takePhoto()
        } else {
            Toast.makeText(this, "Se necesitan permisos para usar la cámara", Toast.LENGTH_SHORT).show()
        }
    }


    private val takePhotoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            photoUri?.let { uri ->
                imageView.setImageURI(uri)
                Toast.makeText(this, "Foto guardada en la galería", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "No se tomó la foto", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camara)

        imageView = findViewById(R.id.imageView)
        val btnTakePhoto: Button = findViewById(R.id.btnTomarFoto)

        btnTakePhoto.setOnClickListener {
            if (checkCameraPermission()) {
                takePhoto()
            } else {
                requestPermissions()
            }
        }
        navegar()
    }

    fun navegar() {
        var btnAtras = findViewById<Button>(R.id.btnAtras)
        btnAtras.setOnClickListener() {
            val atras: Intent = Intent(this, MainActivity::class.java)
            startActivity(atras)
        }
    }

    private fun checkCameraPermission(): Boolean {
        val cameraGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == android.content.pm.PackageManager.PERMISSION_GRANTED
        val storageGranted = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
        return cameraGranted && storageGranted
    }

    private fun requestPermissions() {
        val permissions = mutableListOf(Manifest.permission.CAMERA)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        permissionLauncher.launch(permissions.toTypedArray())
    }

    private fun takePhoto() {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "photo_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/TallerForm")
            }
        }

        photoUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        }

        takePhotoLauncher.launch(intent)
    }
}
