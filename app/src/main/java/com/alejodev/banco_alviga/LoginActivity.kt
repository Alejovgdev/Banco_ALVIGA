package com.alejodev.banco_alviga

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alejodev.banco_alviga.databinding.ActivityLoginBinding

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

                val mainActivity = Intent(this, MainActivity::class.java)
                mainActivity.putExtra("keyDni", dni)
                mainActivity.putExtra("keyPass", password)
                startActivity(mainActivity)

            }


        }

        val salir = binding.salir

        salir.setOnClickListener {
            finish()
        }





    }
}