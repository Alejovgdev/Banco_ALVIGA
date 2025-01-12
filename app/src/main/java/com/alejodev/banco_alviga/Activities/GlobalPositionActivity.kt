package com.alejodev.banco_alviga.Activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alejodev.banco_alviga.Adapters.CuentaAdapter
import com.alejodev.banco_alviga.R
import com.alejodev.banco_alviga.databinding.ActivityGlobalPositionBinding
import com.alejodev.banco_alviga.fragments.CuentaListener
import com.alejodev.banco_alviga.fragments.CuentasFragment
import com.alejodev.banco_alviga.fragments.DefaultFragment
import com.alejodev.banco_alviga.fragments.MovimientoListener
import com.alejodev.banco_alviga.fragments.MovimientosFragment
import com.alejodev.banco_alviga.pojo.Cliente
import com.alejodev.banco_alviga.pojo.Cuenta
import com.alejodev.banco_alviga.pojo.Movimiento

class GlobalPositionActivity : BaseActivity(), CuentaListener{

    private lateinit var binding: ActivityGlobalPositionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.main)

        val cliente = intent.getSerializableExtra("Cliente")
        val cuentasFragment = CuentasFragment.newInstance(cliente as Cliente)

        // Este boolean comprueba en tiempo de ejecucion si el layout que se va a usar es de tipo LARGE
        val isLargeLayout =
            (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE

        val isPortrait =
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        if (isLargeLayout && !isPortrait){

            val cuentasFragment = CuentasFragment.newInstance(cliente)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, cuentasFragment)
                .commit()


            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container2, DefaultFragment())
                .commit()

        }else if (isPortrait && isLargeLayout){

            val cuentasFragment = CuentasFragment.newInstance(cliente)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, cuentasFragment)
                .commit()


            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container2, DefaultFragment())
                .commit()
        } else{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, cuentasFragment)
                .commit()
        }




    }

    override fun onCuentaSelected(cuenta: Cuenta) {

        // Aqui no necesitamos el boolean anterior ya que esta comprobacion no se hará en tiempo de ejecucicon
        var layoutLarge = supportFragmentManager.findFragmentById(R.id.fragment_container2) != null
        val isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        if (layoutLarge && !isPortrait){

            val movimientosFragment = MovimientosFragment.newInstance(cuenta, -1)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container2, movimientosFragment)
                .commit()
        }else if (isPortrait && layoutLarge){
            val movimientosFragment = MovimientosFragment.newInstance(cuenta, -1)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container2, movimientosFragment)
                .commit()
        } else{
            val i = Intent(this, GlobalPositionDetailsActivity::class.java)
            i.putExtra("Cuenta", cuenta)
            startActivity(i)
        }


    }


}