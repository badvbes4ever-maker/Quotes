package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Screens.AllQuotesScreen
import com.example.myapplication.Screens.QuoteScreen
import com.example.myapplication.Screens.SettingsScreen
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.viewModel.MainViewModel
import com.example.myapplication.viewModel.MainViewModelFactory
import com.example.myapplication.viewModel.SettingsViewModel

@Composable
fun AppNavigation(settingsViewModel: SettingsViewModel,
                  onLanguageChanged: () -> Unit)
{
    val navController = rememberNavController()

    val quotesViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(RetrofitInstance.repository))

    NavHost(navController, startDestination = "main") {

        composable("main") {
            QuoteScreen(
                onClick = { navController.navigate("all_quotes") },
                viewModel = quotesViewModel,
                onSettingsClick = { navController.navigate("settings") }
            )
        }

        composable("all_quotes") {
            AllQuotesScreen(
                onClick = { navController.popBackStack() },
                viewModel = quotesViewModel
            )
        }

        composable("settings") {
            SettingsScreen(
                viewModel = settingsViewModel,
                onLanguageChanged = onLanguageChanged,
                onBack = { navController.popBackStack() }
            )
        }
    }
}