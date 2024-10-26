package com.alejodev.banco_alviga

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alejodev.banco_alviga.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)



        val usuario = binding.usuario
        val txtUsuario = usuario.text.toString()
        val dni = intent.getStringExtra("keyDni")
        val pswd = intent.getStringExtra("keyPass")


        usuario.text = "$txtUsuario \n $dni"

        val cambiarContra = binding.buttoncambiarContra

        cambiarContra.setOnClickListener {



            val cambiarClave = Intent(this, CambiarClaveActivity::class.java)
            cambiarClave.putExtra("keyDni", dni)
            cambiarClave.putExtra("keyPass", pswd)

            startActivity(cambiarClave)
        }


    }
}