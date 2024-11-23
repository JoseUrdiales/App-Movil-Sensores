package com.example.tallerform

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Formulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario)
        navegar()
        layoutForm()
    }
    fun navegar() {
        var btnAtras = findViewById<Button>(R.id.btnAtras)
        btnAtras.setOnClickListener() {
            val atras: Intent = Intent(this, MainActivity::class.java)
            startActivity(atras)
        }
    }
    fun layoutForm(){
        val btn_gps = findViewById<Button>(R.id.btnGuardar)
        val tv_nombre = findViewById<TextView>(R.id.etNombre)
        val tv_apellido = findViewById<TextView>(R.id.etApellido)
        val tv_correo = findViewById<TextView>(R.id.etCorreo)
        val tv_telefono = findViewById<TextView>(R.id.etTelefono)
        val tv_ciudad = findViewById<TextView>(R.id.etCiudad)
        btn_gps.setOnClickListener(){
            val message = "Nombre: ${tv_nombre.text}, ${tv_apellido.text}, ${tv_correo.text}, ${tv_telefono.text}, ${tv_ciudad.text}, es estudiante"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

}