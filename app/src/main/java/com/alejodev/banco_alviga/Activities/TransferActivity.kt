package com.alejodev.banco_alviga.Activities

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alejodev.banco_alviga.R
import com.alejodev.banco_alviga.databinding.ActivityTransferBinding

class TransferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.main)

        val cuentasOrigen = listOf("1000000001","1000000002","1000000003","1000000004")

        val spinnerOrigen = binding.spinnerOrigen
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentasOrigen)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerOrigen.adapter = adapter1

        val radioGroup = binding.radioGroup
        var spinnerDestino = binding.spinnerDestino
        var editTextDestino = binding.editTextDestino




        radioGroup.setOnCheckedChangeListener { group, id ->
            when(id){

                R.id.radioButtonSpinner -> {

                    editTextDestino.visibility = View.GONE

                    spinnerDestino.visibility = View.VISIBLE

                    spinnerDestino.adapter = adapter1


                }

                R.id.radioButtonEditText -> {

                    spinnerDestino.visibility = View.GONE

                    editTextDestino.visibility = View.VISIBLE


                }
            }
        }

        val spinnerCantidad = binding.spinnerDivisa
        val divisa = listOf("€","$")


        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, divisa)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCantidad.adapter = adapter2

        val btnEnviar = binding.buttonEnviar
        btnEnviar.setOnClickListener {

            val tv6 = binding.textView6.text.toString()
            val cuentaOrigentxt = spinnerOrigen.selectedItem.toString()

            val cantidad = binding.etCantidad.text.toString()
            val divisaSelec = spinnerCantidad.selectedItem.toString()
            val importe = "Importe: $cantidad$divisaSelec"

            val justificante = binding.enviarJustificante
            var txtJustificante = ""

            if (justificante.isChecked){
                txtJustificante = justificante.text.toString()
            }


            // Determinar el valor de selecionadoRadio al hacer clic en enviar
            val selecionadoRadio = if (radioGroup.checkedRadioButtonId == R.id.radioButtonSpinner) {
                spinnerDestino.selectedItem.toString() // Tomar del spinner
            } else {
                editTextDestino.text.toString() // Tomar del editText
            }


            if (selecionadoRadio.isEmpty()){
                Toast.makeText(this, "Debes seleccionar una cuenta de destino", Toast.LENGTH_SHORT).show()
            }else if (cantidad.isEmpty()){
                Toast.makeText(this, "Debes seleccionar una cantidad", Toast.LENGTH_SHORT).show()
            }else{
                val recibo = """
                $tv6: $cuentaOrigentxt
    
                Cuenta destino: $selecionadoRadio
                
                $importe
                                
                $txtJustificante
            """.trimIndent()


                AlertDialog.Builder(this)
                    .setTitle("Recibo")
                    .setMessage(recibo)
                    .setPositiveButton("OK", null)
                    .show()

            }

        }

        val btnCancelar = binding.buttonCancel
        btnCancelar.setOnClickListener {

            spinnerOrigen.setSelection(0)
            radioGroup.clearCheck()
            val etCant = binding.etCantidad
            etCant.text.clear()
            spinnerCantidad.setSelection(0)
            val justificante = binding.enviarJustificante
            justificante.isChecked = false


        }





    }

}