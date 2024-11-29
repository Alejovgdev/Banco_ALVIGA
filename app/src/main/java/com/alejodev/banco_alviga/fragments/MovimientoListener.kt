package com.alejodev.banco_alviga.fragments

import com.alejodev.banco_alviga.pojo.Movimiento

interface MovimientoListener {
    fun onMovimientoSelected(movimiento: Movimiento)
}