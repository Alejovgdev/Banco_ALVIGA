package com.alejodev.banco_alviga.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alejodev.banco_alviga.bd.MiBancoOperacional
import com.alejodev.banco_alviga.databinding.ActivityCambiarClaveBinding
import com.alejodev.banco_alviga.pojo.Cliente

class CambiarClaveActivity : BaseActivity(){

    private lateinit var binding: ActivityCambiarClaveBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = ActivityCambiarClaveBinding.inflate(layoutInflater)
        setContentView(binding.cambiarClave)

        val usuario = binding.textView7
        val txtUsuario = usuario.text.toString()

        val cliente = intent.getSerializableExtra("Cliente") as? Cliente

        usuario.text = "$txtUsuario ${cliente?.getNombre()}"


        val passold = cliente?.getClaveSeguridad()

        val butnSave = binding.buttonSave



        butnSave.setOnClickListener {




            val passMatch = binding.editTextPasswordOld.text.toString()

            if (passold != passMatch){
                binding.editTextPasswordOld.error = "La contraseña no es correcta"

            }

            val passNew = binding.editTextPasswordnew.text.toString()
            val passNew2 = binding.editTextPasswordnew2.text.toString()

            if (passNew.isEmpty()){
                binding.editTextPasswordnew.error = "Este campo no puede estar vacio"

            }else if (passNew2.isEmpty()){
                binding.editTextPasswordnew2.error = "Este campo no puede estar vacio"

            }else if (passNew != passNew2){

                Toast.makeText(this, "Las nuevas contraseñas no coinciden", Toast.LENGTH_SHORT).show()

            }else{

                if (cliente != null) {

                    cliente.setClaveSeguridad(passNew)

                    val mbo = MiBancoOperacional.getInstance(this)
                    val p = mbo?.changePassword(cliente)

                    if (p == 1){
                        Toast.makeText(this, "Contraseña actualizada con exito", Toast.LENGTH_SHORT).show()
                    }else Toast.makeText(this, "Error al actualizar la contraseña", Toast.LENGTH_SHORT).show()

                }


            }

        }


        val btnReturn = binding.buttonReturn

        btnReturn.setOnClickListener {
            val volver = Intent(this, MainActivity::class.java)
            finish()
        }






    }
}