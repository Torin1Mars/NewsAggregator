package com.example.newsagregatour.MyScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SingleNewsScreen(navController: NavController, newsId: String){

    //TODO Set up design for single screen

    Column(modifier = Modifier.fillMaxSize()
        .padding(top = 30.dp),
        verticalArrangement = Arrangement.Center) {

        Text(text = "Id " + newsId, fontSize = 35.sp )
    }
}