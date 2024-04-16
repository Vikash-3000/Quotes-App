package com.example.firstappwithcompose.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.firstappwithcompose.models.Quote

@Composable()
fun QuoteList(data : ArrayList<Quote>, onClick : (quote : Quote)-> Unit){
    LazyColumn(content = {
        items(data) {
            QuoteListItem(quote = it, onClick)
        }
    })
}