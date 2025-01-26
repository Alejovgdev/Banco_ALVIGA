package com.alejodev.banco_alviga.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alejodev.banco_alviga.Adapters.CajeroAdapter
import com.alejodev.banco_alviga.Adapters.CajeroListener
import com.alejodev.banco_alviga.CajeroApplication
import com.alejodev.banco_alviga.R
import com.alejodev.banco_alviga.databinding.ActivityAtmListBinding
import com.alejodev.banco_alviga.pojo.CajeroEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AtmListActivity : BaseActivity(), CajeroListener {

    private lateinit var binding: ActivityAtmListBinding
    private lateinit var cajeroAdapter: CajeroAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAtmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {

            val cajeros = withContext(Dispatchers.IO){
                CajeroApplication.database.cajeroDao().getAllCajeros()
            }

            linearLayoutManager = LinearLayoutManager(this@AtmListActivity)
            cajeroAdapter = CajeroAdapter(cajeros, this@AtmListActivity)

            binding.recyclerViewCajeros.apply {
                layoutManager = linearLayoutManager
                adapter = cajeroAdapter
            }
        }



    }


    override fun onResume() {
        super.onResume()

        // Cargar de nuevo los datos desde la base de datos
        lifecycleScope.launch(Dispatchers.IO) {
            val cajeros = CajeroApplication.database.cajeroDao().getAllCajeros()
            runOnUiThread {
                // Actualiza el adaptador con los nuevos datos
                (binding.recyclerViewCajeros.adapter as? CajeroAdapter)?.updateData(cajeros)
            }
        }
    }


    override fun onCajeroSelected(c: CajeroEntity) {
        val intent = Intent(this, AtmFormActivity::class.java)
        intent.putExtra("cajero", c)
        startActivity(intent)
    }
}