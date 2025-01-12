package com.alejodev.banco_alviga

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import java.util.Locale

object LocaleHelper {

    fun setLocale(context: Context, language: String): Context{
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources: Resources = context.resources
        val configuration: Configuration= resources.configuration
        configuration.setLocale(locale)

        return context.createConfigurationContext(configuration)
    }

}