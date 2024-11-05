package com.alejodev.banco_alviga

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alejodev.banco_alviga.bd.MiBancoOperacional
import com.alejodev.banco_alviga.databinding.ActivityRegistrarNuevoUsuarioBinding
import com.alejodev.banco_alviga.databinding.ActivitySplashBinding
import com.alejodev.banco_alviga.pojo.Cliente

class RegistrarNuevoUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarNuevoUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrarNuevoUsuarioBinding.inflate(layoutInflater)

        setContentView(binding.main)

        val btnGuardar = binding.btnCrearUsuario

        btnGuardar.setOnClickListener {

            val etNif = binding.etNif
            val etNombre = binding.etNombre.text
            val etApellidos = binding.etApellidos.text
            val etContra = binding.etContra
            val etCorreo = binding.etCorreo.text

            if (etCorreo.isEmpty() || etApellidos.isEmpty() || etNombre.isEmpty()){

                Toast.makeText(this, "Ninguno de los campos puede estar vacio", Toast.LENGTH_SHORT).show()
            }else{


                val dniPattern = Regex("^[0-9]{8}[A-HJ-NP-TV-Z]$")
                val dniValido = dniPattern.matches(etNif.text.toString())

                if (!dniValido){
                    etNif.error = "El dni no es valido"

                }else if (etContra.text.length < 4){
                    etContra.error = "La contraseña debe tener mínimo 4 caracteres"
                }else{

                    var c = Cliente()
                    c.setNif(etNif.text.toString())
                    c.setNombre(etNombre.toString())
                    c.setApellidos(etApellidos.toString())
                    c.setClaveSeguridad(etContra.text.toString())
                    c.setEmail(etCorreo.toString())
                    val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

                    var añadido = mbo?.añadirCliente(c)

                    if (añadido == true){
                        Toast.makeText(this, "Usuario $etNombre registrado correctamente", Toast.LENGTH_SHORT).show()
                        finish()
                    }else Toast.makeText(this, "Hubo un problema al intentar registrar al usuario $etNombre", Toast.LENGTH_SHORT).show()
                }


            }
        }

    }
}