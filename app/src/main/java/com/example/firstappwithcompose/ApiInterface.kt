package com.example.firstappwithcompose

import com.example.firstappwithcompose.models.Quote
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("quotes")
    suspend fun getQuotes() : ArrayList<Quote>
}