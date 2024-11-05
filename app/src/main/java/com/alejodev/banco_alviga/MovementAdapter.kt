package com.alejodev.banco_alviga

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejodev.banco_alviga.databinding.ItemCuentaBinding
import com.alejodev.banco_alviga.databinding.ItemMovementBinding
import com.alejodev.banco_alviga.pojo.Movimiento
import java.text.SimpleDateFormat
import java.util.Date

class MovementAdapter (private val movements: ArrayList<Movimiento>?): RecyclerView.Adapter<MovementAdapter.ViewHolder>(){
    private lateinit var context: Context



    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemMovementBinding.bind(view) // Vinculamos la vista a nuestro adaprter
        val tvTitulo = binding.tvTitulo
        val tvFecha = binding.tvFecha
        val tvImporte = binding.tvImporte
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_movement, parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movement = movements?.get(position)

        with(holder){
            tvTitulo.text = movement?.getDescripcion()
            tvFecha.text = formatDate(movement?.getFechaOperacion())
            val importe = movement?.getImporte() ?: 0f

            if (importe > 0){
                val spannableImporte = SpannableString(importe.toString() + "€").apply {
                    setSpan(ForegroundColorSpan(Color.GREEN), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                tvImporte.text = spannableImporte
            }else{
                val spannableImporte = SpannableString(importe.toString() + "€").apply {
                    setSpan(ForegroundColorSpan(Color.RED), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                tvImporte.text = spannableImporte
            }
        }
    }



    override fun getItemCount(): Int = movements?.size ?:0

    fun formatDate(date: Date?): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return dateFormat.format(date)
    }

}