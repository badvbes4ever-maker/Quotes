package com.example.myapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repo: SettingsRepository
) : ViewModel() {

    val language: StateFlow<String> = repo.languageFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, "ru")

    val themePreset: StateFlow<String> = repo.themeFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, "LIGHT")

    private val _languageChanged = MutableSharedFlow<Unit>()
    val languageChanged = _languageChanged.asSharedFlow()

    fun changeLanguage(lang: String) {
        viewModelScope.launch {
            repo.saveLanguage(lang)
            _languageChanged.emit(Unit)
        }
    }

    fun changeTheme(themeName: String) {
        viewModelScope.launch {
            repo.saveTheme(themeName)
        }
    }
}