package com.example.myapplication

import android.app.Application
import android.content.Context
import com.example.myapplication.data.SettingsKeys
import com.example.myapplication.data.dataStore
import com.example.myapplication.data.settingsDataStore
import com.example.myapplication.repository.SettingsRepository
import com.example.myapplication.utils.applyLocale
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class App : Application() {

    lateinit var settingsRepository: SettingsRepository
        private set

    override fun attachBaseContext(base: Context) {

        val lang = try {
            runBlocking {
                base.createDeviceProtectedStorageContext().settingsDataStore.data
                    .map { it[SettingsKeys.LANGUAGE] ?: "ru" }
                    .first()
            }
        } catch (e: Exception) {
            "ru"
        }

        val newCtx = applyLocale(base, lang)
        super.attachBaseContext(newCtx)
    }

    override fun onCreate() {
        super.onCreate()

        settingsRepository = SettingsRepository(this)
    }
}
