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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_TallerForm)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        navegar()

    }
    fun navegar(){
        var buttonFormulario = findViewById<Button>(R.id.buttonFormulario)
        var buttonLocalizacion = findViewById<Button>(R.id.buttonLocalizacion)
        var buttonPodo = findViewById<Button>(R.id.buttonPodometro)
        var buttonCam = findViewById<Button>(R.id.buttonCamara)
        buttonFormulario.setOnClickListener(){
            val irform: Intent = Intent(this,Formulario::class.java)
            startActivity(irform)
        }
        buttonLocalizacion.setOnClickListener(){
            val irLoc: Intent = Intent(this,Localizacion::class.java)
            startActivity(irLoc)
        }
        buttonPodo.setOnClickListener(){
            val irPodo: Intent = Intent(this,Podometro::class.java)
            startActivity(irPodo)
        }
        buttonCam.setOnClickListener(){
            val irCam: Intent = Intent(this,Camara::class.java)
            startActivity(irCam)
        }
    }



}