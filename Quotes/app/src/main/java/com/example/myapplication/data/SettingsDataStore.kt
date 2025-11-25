package com.example.myapplication.data

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore


val Context.dataStore by preferencesDataStore("settings")
object SettingsKeys {

    val LANGUAGE = stringPreferencesKey("language")
    val THEME = stringPreferencesKey("theme")
}
