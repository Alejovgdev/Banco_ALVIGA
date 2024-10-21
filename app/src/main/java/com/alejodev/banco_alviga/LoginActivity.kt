package com.alejodev.banco_alviga

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alejodev.banco_alviga.databinding.ActivityLoginBinding
import java.io.Console
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        


        val entrar = binding.entrar

        entrar.setOnClickListener {



            val dni = binding.introduce.text.toString()
            val password = binding.Password.text.toString()

            if (dni.isEmpty()){

                binding.introduce.error = "Este campo es obligatorio"

            }else if (password.isEmpty()){

                binding.Password.error = "Este campo es obligatorio"

            }else{
                Log.d("Login Activiti", "$dni")
                val mainActivity = Intent(this, MainActivity::class.java)
                mainActivity.putExtra("keyDni", dni)
                startActivity(mainActivity)
            }


        }

        val salir = binding.salir

        salir.setOnClickListener {
            finish()
        }





    }
}