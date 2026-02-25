package com.example.newsagregatour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.newsagregatour.ViewModels.MainViewModel
import com.example.newsagregatour.data.NewsItem
import com.example.newsagregatour.domain.AppDatabase
import com.example.newsagregatour.ui.theme.NewsAgregatourTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.compose

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Not triggers init block inside View model object
        //val myMainViewModel : MainViewModel by viewModels()

        val myMainViewModel =  ViewModelProvider(this).get(MainViewModel::class.java)

       setContent {
            NewsAgregatourTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(

                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAgregatourTheme {
        Greeting("Android")
    }
}


