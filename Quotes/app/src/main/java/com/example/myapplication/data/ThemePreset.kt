package com.example.myapplication.data

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class ThemePreset {LIGHT, DARK, OCEAN, NIGHTSEA }
private val OceanColors = lightColorScheme(
        primary = Color(0xFF0F828C),
        secondary = Color(0xFF4FC3F7),
        background = Color(0xFFE1F5FE),
    )
private val NightSea = darkColorScheme(
    primary = Color(0xFF0277BD),
    secondary = Color(0xFF065084),
    background = Color(0xFF320A6B),
)

    @Composable
    fun AppTheme(preset: ThemePreset, content: @Composable () -> Unit) {
        val colors = when (preset) {
            ThemePreset.LIGHT -> lightColorScheme()
            ThemePreset.DARK -> darkColorScheme()
            ThemePreset.OCEAN -> OceanColors
            ThemePreset.NIGHTSEA -> NightSea
        }
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
}