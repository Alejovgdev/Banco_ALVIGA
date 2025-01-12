package com.alejodev.banco_alviga.Activities

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager
import com.alejodev.banco_alviga.LocaleHelper
import com.alejodev.banco_alviga.R

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(newBase)
        val idioma = sharedPreferences.getString("idioma", "es") ?: "es"
        val context = LocaleHelper.setLocale(newBase, idioma)
        super.attachBaseContext(context)
    }
}
