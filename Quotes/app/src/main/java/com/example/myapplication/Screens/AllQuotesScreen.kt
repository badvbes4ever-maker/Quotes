package com.example.myapplication.Screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.Screens.Elements.QuoteItem
import com.example.myapplication.data.Response
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.viewModel.MainViewModel
import com.example.myapplication.viewModel.MainViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllQuotesScreen(onClick: () -> Unit, viewModel: MainViewModel) {

    val allQuotes = viewModel.allQuotesState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.all_quotes)) },
                navigationIcon = {
                    IconButton(onClick = { onClick() }) {
                        Icon(Icons.Default.Close, contentDescription = stringResource(R.string.close))
                    }
                }
            )
        }
    ) { padding ->

        when (val state = allQuotes.value) {

            is Response.Loading -> Text(stringResource(R.string.loading), modifier = Modifier.padding(padding))

            is Response.Error -> Text(
                "${stringResource(R.string.error)} ${state.message}",
                modifier = Modifier.padding(padding)
            )

            is Response.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    items(state.data) { quote ->
                        QuoteItem(quote)
                    }
                }
            }

            Response.Idle -> Text(
                stringResource(R.string.no_data),
                modifier = Modifier.padding(padding)
            )
        }
    }
}