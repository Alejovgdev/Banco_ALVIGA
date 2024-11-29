package com.alejodev.banco_alviga.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alejodev.banco_alviga.Activities.GlobalPositionActivity
import com.alejodev.banco_alviga.Adapters.CuentaAdapter
import com.alejodev.banco_alviga.bd.MiBancoOperacional
import com.alejodev.banco_alviga.databinding.FragmentCuentasBinding
import com.alejodev.banco_alviga.pojo.Cliente
import com.alejodev.banco_alviga.pojo.Cuenta
import java.io.Serializable


private const val ARG_Cliente = "cliente"



class CuentasFragment : Fragment(), CuentaListener{

    private lateinit var cuentaAdapter: CuentaAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: FragmentCuentasBinding

    private var cliente: Cliente? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            cliente = it.getSerializable(ARG_Cliente) as? Cliente

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mbo = MiBancoOperacional.getInstance(context)
        var cuentas = mbo?.getCuentas(cliente)


        binding = FragmentCuentasBinding.inflate(inflater, container, false)
        cuentaAdapter = CuentaAdapter(cuentas, this)
        linearLayoutManager = LinearLayoutManager(context)

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = cuentaAdapter
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(c: Cliente) =
            CuentasFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_Cliente, c)

                }
            }
    }

    override fun onCuentaSelected(c: Cuenta) {
        (activity as GlobalPositionActivity).onCuentaSelected(c)
    }

}