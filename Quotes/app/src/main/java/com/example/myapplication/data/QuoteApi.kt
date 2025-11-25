package com.example.myapplication.data

import retrofit2.http.GET

interface QuoteApi {
    @GET("today")
    suspend fun getTodayQuote(): List<Quote>
    @GET("random")
    suspend fun getRandomQuote(): List<Quote>
    @GET("quotes")
    suspend fun getAllQuotes(): List<Quote>
}