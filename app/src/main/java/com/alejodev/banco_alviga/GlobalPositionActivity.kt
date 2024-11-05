package com.alejodev.banco_alviga

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alejodev.banco_alviga.bd.MiBancoOperacional
import com.alejodev.banco_alviga.databinding.ActivityGlobalPositionBinding
import com.alejodev.banco_alviga.pojo.Cliente

class GlobalPositionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlobalPositionBinding
    private lateinit var cuentaAdapter: CuentaAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.main)


        val cliente = intent.getSerializableExtra("Cliente") as? Cliente
        val mbo = MiBancoOperacional.getInstance(this)
        var cuentas = mbo?.getCuentas(cliente)

        cuentaAdapter = CuentaAdapter(cuentas)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = cuentaAdapter

        }






    }

}