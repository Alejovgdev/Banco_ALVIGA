package com.alejodev.banco_alviga.Activities

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alejodev.banco_alviga.Adapters.MovementAdapter
import com.alejodev.banco_alviga.bd.MiBancoOperacional
import com.alejodev.banco_alviga.databinding.ActivityMovementsBinding
import com.alejodev.banco_alviga.fragments.MovimientoListener
import com.alejodev.banco_alviga.pojo.Cliente
import com.alejodev.banco_alviga.pojo.Movimiento

class MovementsActivity : BaseActivity(), MovimientoListener {

    private lateinit var binding: ActivityMovementsBinding
    private lateinit var movementAdapter: MovementAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovementsBinding.inflate(layoutInflater)
        setContentView(binding.main)

        val cliente = intent.getSerializableExtra("Cliente") as? Cliente
        val mbo = MiBancoOperacional.getInstance(this)
        var cuentas = mbo?.getCuentas(cliente) ?: emptyList()
        var numeros = ArrayList<String>()

        // Configura el LayoutManager
        linearLayoutManager = LinearLayoutManager(this) // Aquí
        movementAdapter = MovementAdapter(ArrayList(), this) // Puedes inicializar con una lista vacía
        itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = movementAdapter
            addItemDecoration(itemDecoration)
        }


            for (i in cuentas.indices){
                numeros.add(cuentas[i].getNumeroCuenta().toString())
            }


        var spinner = binding.spinner

        val adapter2 = ArrayAdapter(this, R.layout.simple_spinner_item, numeros)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter2

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val numeroCuenta = numeros[position]
                val cuentaSeleccionada = cuentas.find { it.getNumeroCuenta().toString() == numeroCuenta }
                val movimientos = cuentaSeleccionada?.let { mbo?.getMovimientos(it) as? ArrayList<Movimiento> } ?: ArrayList()

                mostrarMovimientos(movimientos)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Este método se llama cuando no hay ninguna opción seleccionada
                // Aquí puedes manejarlo si es necesario. Por ejemplo, podrías limpiar el RecyclerView.
                mostrarMovimientos(ArrayList()) // Limpia los movimientos si no hay selección
            }
        }





    }

    private fun mostrarMovimientos(movimientos: ArrayList<Movimiento>){
        val adapter = MovementAdapter(movimientos, this)
        binding.recyclerView.adapter = adapter
    }

    override fun onMovimientoSelected(movimiento: Movimiento) {
        TODO("Not yet implemented")
    }
}