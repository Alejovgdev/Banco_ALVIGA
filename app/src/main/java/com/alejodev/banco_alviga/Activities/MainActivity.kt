package com.alejodev.banco_alviga.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alejodev.banco_alviga.bd.MiBancoOperacional
import com.alejodev.banco_alviga.databinding.ActivityMainBinding
import com.alejodev.banco_alviga.pojo.Cliente


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)



        val tvBienvenido = binding.tvBienvenido
        val txtUsuario = tvBienvenido?.text.toString()

        val cliente = intent.getSerializableExtra("Cliente") as? Cliente



        tvBienvenido?.text = "$txtUsuario \n ${cliente?.getNombre()}"

        val cambiarContra = binding.buttoncambiarContra
        cambiarContra.setOnClickListener {



            val cambiarClave = Intent(this, CambiarClaveActivity::class.java)
            cambiarClave.putExtra("Cliente", cliente)

            startActivity(cambiarClave)
        }

        val btnTransfer = binding.buttontransferencias
        btnTransfer.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        val btnPositionActivity = binding.buttonposition
        btnPositionActivity.setOnClickListener {

            val mbo = MiBancoOperacional.getInstance(this)
            var cuentas = mbo?.getCuentas(cliente)

            if (!cuentas.isNullOrEmpty()){
                val intent = Intent(this, GlobalPositionActivity::class.java)
                intent.putExtra("Cliente", cliente)
                startActivity(intent)
            }else{
                AlertDialog.Builder(this)
                    .setTitle("¡Abre tu cuenta ahora!")
                    .setMessage("¡Aún no tienes cuentas! 🎉\n\n" +
                            "¡Abre tu primera cuenta hoy y disfruta de increíbles beneficios!"
                    )
                    .setPositiveButton("OK", null)
                    .show()

            }

        }

        val btnMovement = binding.buttonmovimientos
        btnMovement.setOnClickListener {

            val mbo = MiBancoOperacional.getInstance(this)
            var cuentas = mbo?.getCuentas(cliente)



            if (!cuentas.isNullOrEmpty()){
                val intent = Intent(this, MovementsActivity::class.java)
                intent.putExtra("Cliente", cliente)
                startActivity(intent)
            }else{
                AlertDialog.Builder(this)
                    .setTitle("¡Abre tu cuenta ahora!")
                    .setMessage("¡Aún no tienes cuentas! 🎉\n\n" +
                            "¡Abre tu primera cuenta hoy y disfruta de increíbles beneficios!"
                    )
                    .setPositiveButton("OK", null)
                    .show()

            }

        }

        val btnSalir = binding.buttonsalir
        btnSalir.setOnClickListener {
            finish()
        }

    }
}