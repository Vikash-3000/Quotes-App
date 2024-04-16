package com.example.firstappwithcompose

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.firstappwithcompose.models.Quote
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataManager {

    var list = ArrayList<Quote>()

    val isDataLoaded = mutableStateOf(false)
    val currPage = mutableStateOf(Pages.LISTING)

    var currQuote : Quote? = null

    @OptIn(DelicateCoroutinesApi::class)
    fun loadData(context : Context) {
        val baseUrl = "https://type.fit/api/"
        val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
            .create(ApiInterface::class.java)

        GlobalScope.launch {
            val result = retrofitBuilder.getQuotes()
            list.addAll(result)
            isDataLoaded.value = true
        }
    }

    fun switchPages(quote: Quote?){
        if(currPage.value == Pages.LISTING){
            currQuote = quote
            currPage.value = Pages.DETAIL
        }else{
            currPage.value = Pages.LISTING
        }
    }

}