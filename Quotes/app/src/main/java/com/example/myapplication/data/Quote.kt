package com.example.myapplication.data

data class Quote(
    val q: String, //quote text,
    val a: String,  //author name
    //val i: String, //author image (key required)
    val c: Int // character count
    //val h: String  //pre-formatted HTML quote
)