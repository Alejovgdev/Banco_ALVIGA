package com.alejodev.banco_alviga.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alejodev.banco_alviga.R
import com.alejodev.banco_alviga.databinding.ActivityGlobalPositionBinding
import com.alejodev.banco_alviga.fragments.MovimientoListener
import com.alejodev.banco_alviga.fragments.MovimientosFragment
import com.alejodev.banco_alviga.pojo.Cuenta
import com.alejodev.banco_alviga.pojo.Movimiento

class GlobalPositionDetailsActivity : AppCompatActivity(){

    private lateinit var binding: ActivityGlobalPositionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cuenta = intent.getSerializableExtra("Cuenta") as? Cuenta

        if (cuenta != null){
            val movimientosFragment = MovimientosFragment.newInstance(cuenta)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, movimientosFragment)
                .commit()
        }




    }

}