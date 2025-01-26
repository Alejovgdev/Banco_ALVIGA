package com.alejodev.banco_alviga.Activities

import android.content.Intent
import android.graphics.Color
import android.media.MediaCas.EventListener
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.alejodev.banco_alviga.R
import com.alejodev.banco_alviga.bd.MiBancoOperacional
import com.alejodev.banco_alviga.databinding.ActivityMainBinding
import com.alejodev.banco_alviga.pojo.Cliente
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.navigation.NavigationView


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var cliente: Cliente
    private lateinit var spannable: Spannable
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        val tvBienvenido = binding.tvBienvenido
        val txtUsuario = tvBienvenido?.text.toString()

        cliente = (intent.getSerializableExtra("Cliente") as? Cliente)!!

        tvBienvenido?.text = "$txtUsuario \n ${cliente?.getNombre()}"

        val mbo = MiBancoOperacional.getInstance(this)
        var cuentas = mbo?.getCuentas(cliente)
        var total = 0f

        if (cuentas != null) {
            for (i in cuentas.indices){
                total += cuentas[i].getSaldoActual() ?: 0f
            }
        }

        val tvtotal = binding.tvTotal


        if (total > 0){
             spannable = SpannableString(total.toString() + "€").apply {
                setSpan(ForegroundColorSpan(Color.GREEN), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            tvtotal?.text = spannable
        }else{
            spannable = SpannableString(total.toString() + "€").apply {
                setSpan(ForegroundColorSpan(Color.RED), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            tvtotal?.text = spannable
        }


        val cambiarContra = binding.buttoncambiarContra
        cambiarContra.setOnClickListener {abrirCambiarContra(cliente)}

        val btnTransfer = binding.buttontransferencias
        btnTransfer.setOnClickListener {abrirTransferencias(cliente)}

        val btnPositionActivity = binding.buttonposition
        btnPositionActivity.setOnClickListener {abrirPosition(cliente)}

        val btnMovement = binding.buttonmovimientos
        btnMovement.setOnClickListener {abrirMovimientos(cliente)}

        drawerLayout = findViewById<DrawerLayout>(R.id.main)

        val bottomBar = findViewById<BottomAppBar>(R.id.bottomAppBar)
        setSupportActionBar(bottomBar)

        navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        var toggle = ActionBarDrawerToggle(this, drawerLayout, bottomBar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navHeader = navigationView.getHeaderView(0)
        val usertxt = navHeader.findViewById<TextView>(R.id.username)
        usertxt.text = cliente.getNombre()



        val btnSalir = binding.buttonsalir
        btnSalir.setOnClickListener {
            finish()
        }

        val btnAtm = binding.buttoncajeros
        btnAtm?.setOnClickListener {
            abrirAtmMng(cliente)
        }

    }

    private fun abrirAtmMng(cliente: Cliente) {
        Log.d("es Admin-------------------", cliente.getIsAdmin().toString())
       if (cliente.getIsAdmin() == 1){
           val intent = Intent(this, AtmManagmentActivity::class.java)
           startActivity(intent)
       }

    }

    fun abrirPosition(cliente : Cliente){


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

    fun abrirMovimientos(cliente: Cliente){

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

    fun abrirTransferencias(cliente: Cliente){
        val intent = Intent(this, TransferActivity::class.java)
        intent.putExtra("Cliente", cliente)
        startActivity(intent)
    }

    fun abrirCambiarContra(cliente: Cliente){
        val cambiarClave = Intent(this, CambiarClaveActivity::class.java)
        cambiarClave.putExtra("Cliente", cliente)
        startActivity(cambiarClave)
    }

    fun abrirPreferencias(cliente: Cliente){
        val preferencias = Intent(this, SettingsActivity::class.java)
        preferencias.putExtra("Cliente", cliente)
        startActivity(preferencias)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_home -> {
                drawerLayout.closeDrawer(GravityCompat.START)

            }
            R.id.nav_posicion ->{
                abrirPosition(cliente)
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_movimientos ->{
                abrirMovimientos(cliente)
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_transferencias ->{
                abrirTransferencias(cliente)
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_cambiar_contra ->{
                abrirCambiarContra(cliente)
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_config ->{
                abrirPreferencias(cliente)
            }
            R.id.nav_cajeros ->{
                abrirAtmMng(cliente)
            }
            R.id.nav_logout->finish()
        }
        return true
    }

}