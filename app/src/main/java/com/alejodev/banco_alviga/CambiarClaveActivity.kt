package com.alejodev.banco_alviga

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alejodev.banco_alviga.databinding.ActivityCambiarClaveBinding

class CambiarClaveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCambiarClaveBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = ActivityCambiarClaveBinding.inflate(layoutInflater)
        setContentView(binding.cambiarClave)

        val usuario = binding.textView7
        val txtUsuario = usuario.text.toString()
        val dni = intent.getStringExtra("keyDni")
        usuario.text = "$txtUsuario $dni"

        val passold = intent.getStringExtra("keyPass")

        val butnSave = binding.buttonSave



        butnSave.setOnClickListener {

            val passMatch = binding.editTextPasswordM.text.toString()

            if (passold != passMatch){
                binding.editTextPasswordM.error = "La contraseña no es correcta"

            }

            val passNew = binding.editTextPasswordnew.text.toString()
            val passNew2 = binding.editTextPasswordnew2.text.toString()

            if (passNew.isEmpty()){
                binding.editTextPasswordnew.error = "Este campo no puede estar vacio"

            }else if (passNew2.isEmpty()){
                binding.editTextPasswordnew2.error = "Este campo no puede estar vacio"

            }else if (passNew != passNew2){

                Toast.makeText(this, "Las nuevas contraseñas no coinciden", Toast.LENGTH_SHORT).show()

            }

        }


        val btnReturn = binding.buttonReturn

        btnReturn.setOnClickListener {
            val volver = Intent(this, MainActivity::class.java)
            startActivity(volver)
            finish()
        }






    }
}