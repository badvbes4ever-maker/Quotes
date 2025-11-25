package com.example.myapplication.data

import com.example.myapplication.repository.MainRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://zenquotes.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: QuoteApi by lazy {
        retrofit.create(QuoteApi::class.java)
    }
    val repository by lazy {
        MainRepository(api)
    }
}