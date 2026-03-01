package com.example.newsagregatour.MyScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun MainScreen(navHostController: NavHostController){
    val modifier = Modifier

    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){

        Text("Main Screen", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }

}

