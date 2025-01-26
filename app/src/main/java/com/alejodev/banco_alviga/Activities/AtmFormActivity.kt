package com.alejodev.banco_alviga.Activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.alejodev.banco_alviga.CajeroApplication
import com.alejodev.banco_alviga.R
import com.alejodev.banco_alviga.databinding.ActivityAtmFormBinding
import com.alejodev.banco_alviga.databinding.ActivityAtmListBinding
import com.alejodev.banco_alviga.pojo.CajeroEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AtmFormActivity : BaseActivity() {

    private lateinit var binding: ActivityAtmFormBinding
    private var cajero: CajeroEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAtmFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cajero = intent.getSerializableExtra("cajero") as? CajeroEntity

        if (cajero != null) {
            binding.editTextDireccion.setText(cajero?.direccion)
            binding.editTextLongitud.setText(cajero?.longitud.toString())
            binding.editTextLatitud.setText(cajero?.latitud.toString())
        }

        val direccion = binding.editTextDireccion.text
        val latitud = binding.editTextLatitud.text
        val longitud = binding.editTextLongitud.text


        binding.btnSave.setOnClickListener {

            if (!direccion.isNullOrEmpty() && !latitud.isNullOrEmpty() && !longitud.isNullOrEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {


                    val cajeroNuevo = CajeroEntity(
                        0,
                        direccion.toString(),
                        latitud.toString().toDouble(),
                        longitud.toString().toDouble(),
                        ""
                    )
                    CajeroApplication.database.cajeroDao().addCajero(cajeroNuevo)
                    setResult(RESULT_OK)
                    finish()


                }
            } else {
                Toast.makeText(
                    this@AtmFormActivity,
                    "Debes rellenar todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

        val opcionesSpin = listOf("", "Eliminar", "Actualizar")

        var spinner = binding.spinner2

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesSpin)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val opcionSelec = opcionesSpin[position]

                when (opcionSelec) {
                    "Eliminar" -> {
                        lifecycleScope.launch(Dispatchers.IO) {
                            cajero?.let { CajeroApplication.database.cajeroDao().deleteCajero(it) }
                        }
                        setResult(RESULT_OK)
                        finish()
                    }

                    "Actualizar" -> {

                        val dir = binding.editTextDireccion.text
                        val lat = binding.editTextLatitud.text
                        val lon = binding.editTextLongitud.text

                        if (!direccion.isNullOrEmpty() && !latitud.isNullOrEmpty() && !longitud.isNullOrEmpty()) {

                            lifecycleScope.launch(Dispatchers.IO) {
                                cajero?.let {
                                    it.direccion = dir.toString()
                                    it.latitud = lat.toString().toDouble()
                                    it.longitud = lon.toString().toDouble()
                                    CajeroApplication.database.cajeroDao().updateCajero(it)
                                }
                            }
                            setResult(RESULT_OK)
                            finish()
                        }else{
                            Toast.makeText(
                                this@AtmFormActivity,
                                getString(R.string.rellenar_campos),
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


    }

}