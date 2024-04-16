package com.example.firstappwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.firstappwithcompose.screens.QuoteDetails
import com.example.firstappwithcompose.screens.QuoteListScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            delay(1500)
            DataManager.loadData(applicationContext)
        }
        setContent {
            App()
        }
    }
}

@Composable()
fun App(){
    if(DataManager.isDataLoaded.value){

        if(DataManager.currPage.value == Pages.LISTING){
            QuoteListScreen(data = DataManager.list){
                DataManager.switchPages(it)
            }
        }else{
            DataManager.currQuote?.let { QuoteDetails(quote = it) }
        }

    }else{

        // CircularProgressIndicator is generally used
        // at the loading screen and it indicates that
        // some progress is going on so please wait.
        Column(
            // we are using column to align our
            // imageview to center of the screen.
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),

            // below line is used for specifying
            // vertical arrangement.
            verticalArrangement = Arrangement.Center,

            // below line is used for specifying
            // horizontal arrangement.
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            // below line is use to display
            // a circular progress bar.
            CircularProgressIndicator(
                // below line is use to add padding
                // to our progress bar.
                modifier = Modifier.padding(16.dp),

                // below line is use to add color
                // to our progress bar.
                color = colorResource(id = R.color.grey),

                // below line is use to add stroke
                // width to our progress bar.
                strokeWidth = Dp(value = 4F)
            )
        }

    }
}

enum class Pages{
    LISTING,
    DETAIL
}

