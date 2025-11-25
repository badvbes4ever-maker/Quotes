package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.example.myapplication.data.AppTheme
import com.example.myapplication.navigation.AppNavigation
import com.example.myapplication.utils.applyLocale
import com.example.myapplication.utils.restartApp
import com.example.myapplication.utils.toThemePreset
import com.example.myapplication.viewModel.SettingsViewModel
import com.example.myapplication.viewModel.SettingsViewModelFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class MainActivity : ComponentActivity() {
    private val settingsViewModel: SettingsViewModel by viewModels {
        SettingsViewModelFactory(
            (application as App).settingsRepository
        )
    }

    override fun attachBaseContext(newBase: Context) {
        val lang = runBlocking {
            (newBase.applicationContext as App).settingsRepository.languageFlow.first()
        }

        val context = applyLocale(newBase, lang)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val preset = settingsViewModel.themePreset.collectAsState().value.toThemePreset()

            AppTheme(preset) {
                AppNavigation(
                    settingsViewModel = settingsViewModel,
                    onLanguageChanged = {
                        this@MainActivity.recreate()
                    }
                )
            }
        }
    }
}