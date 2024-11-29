package com.alejodev.banco_alviga.Adapters

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejodev.banco_alviga.R
import com.alejodev.banco_alviga.databinding.ItemCuentaBinding
import com.alejodev.banco_alviga.fragments.CuentaListener
import com.alejodev.banco_alviga.pojo.Cuenta

class CuentaAdapter(private val cuentas: ArrayList<Cuenta>?, private val listener: CuentaListener): RecyclerView.Adapter<CuentaAdapter.ViewHolder>(){

    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {  // Inflar la vista del recycler
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_cuenta, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cuenta = cuentas?.get(position)
        with(holder){
            itemView.setOnClickListener{
                if (cuenta != null) {
                    listener.onCuentaSelected(cuenta)
                }
            }
            tvCuenta.text = cuenta?.getNumeroCuenta()
            val saldoActual = cuenta?.getSaldoActual() ?: 0f

            if (saldoActual > 0){
                val spannableSaldo = SpannableString(saldoActual.toString() + "€").apply {
                    setSpan(ForegroundColorSpan(Color.GREEN), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                tvSaldo.text = spannableSaldo
            }else{
                val spannableSaldo = SpannableString(saldoActual.toString() + "€").apply {
                    setSpan(ForegroundColorSpan(Color.RED), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                tvSaldo.text = spannableSaldo
            }



        }
    }


    override fun getItemCount(): Int = cuentas?.size ?: 0



    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemCuentaBinding.bind(view) // Vinculamos la vista a nuestro adaprter
        val tvCuenta = binding.tvCuenta
        val tvSaldo = binding.tvSaldo
    }


}