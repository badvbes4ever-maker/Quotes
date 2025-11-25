package com.example.myapplication.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.myapplication.data.SettingsKeys
import com.example.myapplication.data.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(private val context: Context) {
    val languageFlow: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[SettingsKeys.LANGUAGE] ?: "ru" }

    val themeFlow: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[SettingsKeys.THEME] ?: "LIGHT" }

    suspend fun saveLanguage(lang: String) {
        context.dataStore.edit { prefs ->
            prefs[SettingsKeys.LANGUAGE] = lang
        }
    }

    suspend fun saveTheme(themeName: String) {
        context.dataStore.edit { prefs ->
            prefs[SettingsKeys.THEME] = themeName
        }
    }
}