package com.alejodev.banco_alviga.Adapters

import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejodev.banco_alviga.R
import com.alejodev.banco_alviga.databinding.ItemCajeroBinding
import com.alejodev.banco_alviga.pojo.CajeroEntity

class CajeroAdapter(private val cajeros: MutableList<CajeroEntity>, private val listener: CajeroListener): RecyclerView.Adapter<CajeroAdapter.ViewHolder>() {


    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemCajeroBinding.bind(view)
        val tvCajero = binding.tvAtm
        val tvDireccion = binding.tvDireccion
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_cajero, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cajeros.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cajero = cajeros.get(position)
        with(holder){
            itemView.setOnClickListener{
                listener.onCajeroSelected(cajero)
            }
            var pos = position + 1
            var txt = context.getString(R.string.cajero)
            tvCajero.text = "$txt $pos"
            tvDireccion.text = cajero.direccion
        }
    }

    fun updateData(newcajeros: MutableList<CajeroEntity>) {
        cajeros.clear()
        cajeros.addAll(newcajeros)
        notifyDataSetChanged()
    }
}

interface CajeroListener{
    fun onCajeroSelected(c: CajeroEntity)
}