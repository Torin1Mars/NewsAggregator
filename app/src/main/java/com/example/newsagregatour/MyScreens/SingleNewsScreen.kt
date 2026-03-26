package com.example.newsagregatour.MyScreens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.util.Logger
import com.example.newsagregatour.ViewModels.MainViewModel
import com.example.newsagregatour.data.NewsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
@Composable
fun SingleNewsScreen(newsId: String){

    val viewModel  = hiltViewModel<MainViewModel>()

    var currentNewsItem by remember {mutableStateOf<NewsItem?>(null)}
    CoroutineScope(Dispatchers.IO).launch{
        currentNewsItem = viewModel.getNewsById(newsId.toInt())
    }

    Column(modifier = Modifier.fillMaxSize()
        .padding(top = 30.dp),
        verticalArrangement = Arrangement.Center) {

        currentNewsItem?.let {
            Text(text = it.newsTitle, fontSize = 35.sp) }

        Text(text = "Id " + newsId, fontSize = 35.sp)
    }
}