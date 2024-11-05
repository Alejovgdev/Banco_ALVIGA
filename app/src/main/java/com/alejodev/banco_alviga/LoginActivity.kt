package com.alejodev.banco_alviga

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alejodev.banco_alviga.bd.MiBancoOperacional
import com.alejodev.banco_alviga.databinding.ActivityLoginBinding
import com.alejodev.banco_alviga.pojo.Cliente

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        


        val entrar = binding.entrar

        entrar.setOnClickListener {

            val etUsuario = binding.etUser.text
            val etContraseña = binding.etPassword.text

            if (etUsuario.isEmpty() || etContraseña.isEmpty()){
                Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_LONG).show()
            }else {


                val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

                // Introducimos los datos como si fuera la pantalla inicial

                var cliente = Cliente()
                cliente.setNif(binding.etUser.text.toString())
                cliente.setClaveSeguridad(binding.etPassword.text.toString())

                // Logueamos al cliente
                val clienteLogeado = mbo?.login(cliente)
                if (clienteLogeado == null) {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("Cliente", clienteLogeado)
                    startActivity(intent)
                }

            }


        }

        val btnNuevoUsuario = binding.btnNuevoUsuario

        btnNuevoUsuario.setOnClickListener {

            val intent = Intent(this, RegistrarNuevoUsuario::class.java)
            startActivity(intent)

        }

        val salir = binding.salir

        salir.setOnClickListener {
            finish()
        }





    }
}