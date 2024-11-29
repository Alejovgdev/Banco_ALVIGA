package com.alejodev.banco_alviga.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alejodev.banco_alviga.Activities.GlobalPositionActivity
import com.alejodev.banco_alviga.Activities.GlobalPositionDetailsActivity
import com.alejodev.banco_alviga.Activities.JokeActivity
import com.alejodev.banco_alviga.Activities.MainActivity
import com.alejodev.banco_alviga.Adapters.MovementAdapter
import com.alejodev.banco_alviga.R
import com.alejodev.banco_alviga.bd.MiBancoOperacional
import com.alejodev.banco_alviga.databinding.DialogPersonalBinding
import com.alejodev.banco_alviga.databinding.FragmentMovimientosBinding
import com.alejodev.banco_alviga.pojo.Cliente
import com.alejodev.banco_alviga.pojo.Cuenta
import com.alejodev.banco_alviga.pojo.Movimiento
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Date


private const val ARG_CUENTA = "cuenta"


class MovimientosFragment : Fragment(), MovimientoListener {

    private var cuenta: Cuenta? = null

    private lateinit var binding: FragmentMovimientosBinding
    private lateinit var movementAdapter: MovementAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cuenta = it.getSerializable(ARG_CUENTA) as? Cuenta
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mbo = MiBancoOperacional.getInstance(context)
        val moviemientos = mbo?.getMovimientos(cuenta) as? ArrayList<Movimiento> ?: ArrayList()

        binding = FragmentMovimientosBinding.inflate(inflater, container, false)
        movementAdapter = MovementAdapter(moviemientos, this)
        linearLayoutManager = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = movementAdapter
            addItemDecoration(itemDecoration)
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(cuenta: Cuenta) =
            MovimientosFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CUENTA, cuenta)
                }
            }
    }

    override fun onMovimientoSelected(movimiento: Movimiento) {


        //Inflamos la vista personalizada

        val dialogoBinding = DialogPersonalBinding.inflate(layoutInflater)

        dialogoBinding.tvId.text = "ID: ${movimiento.getId().toString()}"
        dialogoBinding.tvTipo.text = "Tipo: ${movimiento.getTipo().toString()}"
        dialogoBinding.tvFecha.text = "Fecha: ${formatDate(movimiento.getFechaOperacion())}"
        dialogoBinding.tvDescripcion.text = "Descripcion: ${movimiento.getDescripcion()}"


        //Con el setView le pasamos la vista
        MaterialAlertDialogBuilder(requireContext())
            .setView(dialogoBinding.root)
            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, i ->
                //Código a ejecutar en caso de Aceptar
                val intent = Intent(context, JokeActivity::class.java)
                startActivity(intent)
            })
            .show()

    }

    fun formatDate(date: Date?): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return dateFormat.format(date)
    }
}