package com.example.myapplication.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.utils.restartApp
import com.example.myapplication.viewModel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel, onLanguageChanged:() -> Unit,onBack: () -> Unit
) {
    val langState = viewModel.language.collectAsState()
    val themeState = viewModel.themePreset.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.languageChanged.collect {
            restartApp(context)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Text(
                "Choose a language",
                color = MaterialTheme.colorScheme.primary
            )
            val languages = listOf("ru" to "Русский", "en" to "English", "tr" to "Türkçe")
            languages.forEach { (code, label) ->
                val selected = code == langState.value
                androidx.compose.foundation.layout.Row(
                    modifier = Modifier
                        .clickable {
                            if (!selected) {
                                viewModel.changeLanguage(code)
                            }
                        }
                        .padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = selected, onClick = {
                            if (!selected) {
                                viewModel.changeLanguage(code)
                            }
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = label, modifier = Modifier.padding(start = 8.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Text(
                stringResource(R.string.choose_theme), modifier = Modifier.padding(top = 16.dp),
                color = MaterialTheme.colorScheme.primary
            )
            val presets = listOf(
                "LIGHT" to stringResource(R.string.standart),
                "DARK" to stringResource(R.string.dark),
                "OCEAN" to "Ocean",
                "NIGHTSEA" to "NightSea"
            )
            presets.forEach { (key, label) ->
                val selected = key == themeState.value
                androidx.compose.foundation.layout.Row(
                    modifier = Modifier
                        .clickable { if (!selected) viewModel.changeTheme(key) }
                        .padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = selected,
                        onClick = { if (!selected) viewModel.changeTheme(key) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = label, modifier = Modifier.padding(start = 8.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}