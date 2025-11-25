package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Quote
import com.example.myapplication.data.Response
import com.example.myapplication.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository): ViewModel() {
    private val _randomQuoteState =
        MutableStateFlow<Response<List<Quote>>>(Response.Idle)
    val randomQuoteState = _randomQuoteState.asStateFlow()

    private val _todayQuoteState =
        MutableStateFlow<Response<List<Quote>>>(Response.Idle)
    val todayQuoteState = _todayQuoteState.asStateFlow()
    private val _allQuoteState =
        MutableStateFlow<Response<List<Quote>>>(Response.Idle)
    val allQuotesState = _allQuoteState.asStateFlow()


    fun loadRandomQuote() {
        viewModelScope.launch {
            _randomQuoteState.value = Response.Loading

            val response = repository.getRandomQuote()

            _randomQuoteState.value = when (response) {
                is Response.Success -> {
                    val fixedList = response.data.map { quote ->
                        quote.copy(c = quote.q.length)
                    }
                    Response.Success(fixedList)
                }
                else -> response
            }
        }
    }

    fun loadTodayQuote() {
        viewModelScope.launch {
            _todayQuoteState.value = Response.Loading

            val response = repository.getTodayQuote()

            _todayQuoteState.value = when (response) {
                is Response.Success -> {
                    val fixedList = response.data.map { quote ->
                        quote.copy(c = quote.q.length)
                    }
                    Response.Success(fixedList)
                }
                else -> response
            }
        }
    }
    fun loadAllQuote(){
        viewModelScope.launch {
            _allQuoteState.value = Response.Loading
            _allQuoteState.value = repository.getAllQuotes()
        }
    }
}