package com.alejodev.banco_alviga.Activities

import android.content.Intent
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.alejodev.banco_alviga.LocaleHelper
import com.alejodev.banco_alviga.R

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val languagePreference = findPreference<ListPreference>("idioma")
            languagePreference?.setOnPreferenceChangeListener { _, newValue ->
                val lenguaje = newValue as String
                val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
                with(sharedPreferences.edit()){
                    putString("idioma", lenguaje)
                    apply()
                }

                LocaleHelper.setLocale(requireContext(), lenguaje)

                // Relanzamos la app para que todos los cambios se apliquen a todas las activities
                // Cada vez que creemos un acitvity heredara de base activity para que se pueda manejar el idioma
                restartApp()
                true
            }

        }

        private fun restartApp() {
            // Reiniciar la aplicación
            val intent = requireContext().packageManager
                .getLaunchIntentForPackage(requireContext().packageName)
            intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }


}