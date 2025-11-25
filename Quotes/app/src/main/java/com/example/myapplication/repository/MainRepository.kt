package com.example.myapplication.repository

import com.example.myapplication.data.Quote
import com.example.myapplication.data.QuoteApi
import com.example.myapplication.data.Response

class MainRepository(private val api: QuoteApi) {

    suspend fun getRandomQuote(): Response<List<Quote>> {
        return safeApiCall { api.getRandomQuote() }
    }

    suspend fun getTodayQuote(): Response<List<Quote>> {
        return safeApiCall { api.getTodayQuote() }
    }
    suspend fun getAllQuotes(): Response<List<Quote>>{
        return safeApiCall {api.getAllQuotes()
        }
    }

    private inline fun <T> safeApiCall(apiCall: () -> T): Response<T> {
        return try {
            Response.Success(apiCall())
        } catch (e: Exception) {
            Response.Error(e.message ?: "Unknown error")
        }
    }
}