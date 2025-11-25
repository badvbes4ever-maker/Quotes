package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.SettingsRepository

class SettingsViewModelFactory(
    private val repo: SettingsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}