package com.example.myapplication.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.data.Response
import com.example.myapplication.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun QuoteScreen(onClick:() -> Unit ,
                viewModel: MainViewModel,
                onSettingsClick:()-> Unit) {

    var selectedOption by remember { mutableStateOf("random") }

    val randomState = viewModel.randomQuoteState.collectAsState()
    val todayState = viewModel.todayQuoteState.collectAsState()
    val allQuotes = viewModel.allQuotesState.collectAsState()

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.Quotes)) },
                actions = {
                    IconButton(onClick = { onSettingsClick() }) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = stringResource(R.string.settings)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(stringResource(R.string.choose_action),color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption == "random",
                    onClick = { selectedOption = "random" },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.primary
                    )

                )
                Text(stringResource(R.string.random_quote))
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption == "today",
                    onClick = { selectedOption = "today" },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.primary
                    )
                )
                Text(stringResource(R.string.today_quote))
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption == "quotes",
                    onClick = { selectedOption = "quotes" },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.primary
                    )
                )
                Text(stringResource(R.string.all_quotes))
            }

            Spacer(Modifier.height(24.dp))

            Button(onClick = {
                when (selectedOption) {
                    "random" -> viewModel.loadRandomQuote()
                    "today" -> viewModel.loadTodayQuote()
                    "quotes" -> viewModel.loadAllQuote()
                }
            }
            ) {
                Text(stringResource(R.string.show))
            }

            Spacer(Modifier.height(24.dp))

            when (selectedOption) {

                "random" -> {
                    when (val state = randomState.value) {
                        is Response.Loading -> Text(stringResource(R.string.loading))
                        is Response.Error -> Text("${stringResource(R.string.error)} ${state.message}")
                        is Response.Success -> {
                            val quote = state.data.first()
                            Text("\"${quote.q}\" — ${quote.a}")
                            Text("${stringResource(R.string.chars)}: ${quote.c}",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Response.Idle -> Text(stringResource(R.string.press_button))
                    }
                }

                "today" -> {
                    when (val state = todayState.value) {
                        is Response.Loading -> Text(stringResource(R.string.loading))
                        is Response.Error -> Text("${stringResource(R.string.error)} ${state.message}")
                        is Response.Success -> {
                            val quote = state.data.first()
                            Text("\"${quote.q}\" — ${quote.a}")
                            Text("${stringResource(R.string.chars)}: ${quote.c}",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Response.Idle -> Text(stringResource(R.string.press_button))
                    }
                }

                "quotes" -> {
                    when (val state = allQuotes.value) {
                        is Response.Loading -> Text(stringResource(R.string.loading))
                        is Response.Error -> Text("${stringResource(R.string.error)} ${state.message}")
                        is Response.Success -> {
                            onClick()
                        }
                        Response.Idle -> Text(stringResource(R.string.press_button))
                    }
                }
            }
        }
    }
}