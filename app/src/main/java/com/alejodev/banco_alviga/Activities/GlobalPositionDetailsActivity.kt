package com.alejodev.banco_alviga.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.alejodev.banco_alviga.R
import com.alejodev.banco_alviga.databinding.ActivityGlobalPositionBinding
import com.alejodev.banco_alviga.databinding.ActivityGlobalPositionDetailsBinding
import com.alejodev.banco_alviga.fragments.MovimientoListener
import com.alejodev.banco_alviga.fragments.MovimientosFragment
import com.alejodev.banco_alviga.pojo.Cuenta
import com.alejodev.banco_alviga.pojo.Movimiento

class GlobalPositionDetailsActivity : AppCompatActivity(){

    private lateinit var binding: ActivityGlobalPositionDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGlobalPositionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.main)

        val cuenta = intent.getSerializableExtra("Cuenta") as? Cuenta

        if (cuenta != null){
            val movimientosFragment = MovimientosFragment.newInstance(cuenta, 0)

            replaceFragment(movimientosFragment)
        }

        binding.bottomNav.setOnNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId){
                R.id.nav_todos-> {
                    if (cuenta != null){
                        val movimientosFragment = MovimientosFragment.newInstance(cuenta, -1)
                        replaceFragment(movimientosFragment)
                    }
                }
                R.id.nav_cero->{
                    if (cuenta != null){
                        val movimientosFragment = MovimientosFragment.newInstance(cuenta, 0)
                        replaceFragment(movimientosFragment)
                    }
                }
                R.id.nav_uno->{
                    if (cuenta != null){
                        val movimientosFragment = MovimientosFragment.newInstance(cuenta, 1)
                        replaceFragment(movimientosFragment)
                    }
                }
                R.id.nav_dos->{
                    if (cuenta != null){
                        val movimientosFragment = MovimientosFragment.newInstance(cuenta, 2)
                        replaceFragment(movimientosFragment)
                    }
                }
            }


            false
        }




    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_containerDetails, fragment)
            .commit()
    }

}