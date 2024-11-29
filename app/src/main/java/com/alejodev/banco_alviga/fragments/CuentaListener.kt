package com.alejodev.banco_alviga.fragments

import com.alejodev.banco_alviga.pojo.Cuenta

interface CuentaListener {
    fun onCuentaSelected(c: Cuenta)
}