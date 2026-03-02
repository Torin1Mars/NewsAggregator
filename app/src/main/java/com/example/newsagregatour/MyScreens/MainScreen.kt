package com.example.newsagregatour.MyScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.newsagregatour.ui.theme.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.room.util.TableInfo
import com.example.newsagregatour.ViewModels.MainViewModel


@Composable
fun MainScreen(navHostController: NavHostController, mainViewModel: MainViewModel){
    val modifier = Modifier

    val gradientColors: List<Color> = listOf(mainBg, Color.White)

    Box(
        modifier = modifier.fillMaxSize()
            .background(Brush.verticalGradient(colors = gradientColors))
    ){
        Scaffold(modifier = modifier.fillMaxSize(),
            bottomBar = {BottomBar(modifier)})
        {

        }
    }
}

@Composable
fun BottomBar(modifier: Modifier){
    Row(modifier = modifier.fillMaxWidth()
        .height(100.dp))
    {

    }
}

