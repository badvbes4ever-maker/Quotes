package com.example.myapplication.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.myapplication.data.ThemePreset
import java.util.Locale

fun applyLocale(context: Context, lang: String): Context {
    val locale = Locale.forLanguageTag(lang)
    Locale.setDefault(locale)

    val config = context.resources.configuration
    config.setLocale(locale)

    return context.createConfigurationContext(config)
}

fun restartApp(context: Context) {
    val activity = context as? Activity ?: return
    val intent = activity.intent
    activity.finish()
    activity.startActivity(intent)
}
fun String.toThemePreset(): ThemePreset {
    return try {
        ThemePreset.valueOf(this)
    } catch (e: IllegalArgumentException) {
        ThemePreset.LIGHT
    }
}