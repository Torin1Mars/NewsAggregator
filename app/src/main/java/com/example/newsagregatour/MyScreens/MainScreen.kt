package com.example.newsagregatour.MyScreens

import android.widget.Button
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsagregatour.R
import com.example.newsagregatour.ViewModels.MainViewModel


@Composable
fun MainScreen(navHostController: NavHostController, mainViewModel: MainViewModel){
    val modifier = Modifier

    var showBottomSplashScreen by remember { mutableStateOf<Boolean>(false) }

    val gradientColors = listOf(Color(0xFF4CAF50), Color(0xFF8BC34A), Color(0xFFCDDC39))

    Box(
        modifier = modifier.fillMaxSize()
            .background(Brush.verticalGradient(colors = gradientColors))
    ){

        Surface(modifier = modifier.fillMaxSize()
            .padding(10.dp),
            color = Color.White.copy(alpha = 0.5f),
            shape = RoundedCornerShape(5)) {

            Scaffold(modifier = modifier.fillMaxSize(),
                containerColor = (Color.Transparent),

                topBar = {TopBar(modifier)},
                bottomBar = {BottomBar(modifier, {}, {showBottomSplashScreen = !showBottomSplashScreen})})
            {
                if (showBottomSplashScreen){
                    bottomSplashScreen(modifier)

                }

            }
        }
    }
}

@Composable
fun BottomBar(modifier: Modifier,
              addNewsCategory:()->Unit,
              showBottomSplashScreen:()-> Unit ){
    Row(modifier = modifier.fillMaxWidth()
        .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround)
    {
        IconButton(onClick = addNewsCategory ){
            Icon(imageVector = Icons.Default.Refresh,
                contentDescription = "Refresh")
        }

        IconButton(onClick = showBottomSplashScreen ){
            Icon(imageVector = Icons.Default.Menu,
                contentDescription = "Menu")
        }
    }
}

@Composable
fun TopBar(modifier: Modifier)
{
    Row(modifier = modifier.height(90.dp)
        .padding(vertical = 30.dp)
        .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically)
    {
        Image(painterResource(R.drawable.mainscreennewsicon),
            "App News Icon",
            contentScale = ContentScale.Fit)

        Spacer(modifier=modifier.width(20.dp))

        Text(stringResource(R.string.App_MainScreen_Title),
            fontSize = 18.sp)
    }
}

@Composable
fun bottomSplashScreen(modifier: Modifier){

    SimpleHorizontalSlider()
/*
    Column {
        Spacer(modifier = modifier.fillMaxWidth().weight(1f))

        Surface(modifier = modifier.fillMaxWidth()
            .height(400.dp)
            .padding(bottom = 50.dp)
            .offset (y = -screenOffset) ,
            shape = RoundedCornerShape(5),
            color = Color.Gray.copy(alpha = 0.5f)){
        }
    }*/
}

@Composable
fun SimpleHorizontalSlider() {
    var sliderValue by remember { mutableStateOf(0.5f) } // Initial value

    Column {
        Slider(
            value = sliderValue
            onValueChange = { newValue ->
                sliderValue = newValue // Update the state when the value changes
            },
            valueRange = 0f..1f, // Define the range of values (0.0 to 1.0)
            steps = 0 // 0 steps makes it a continuous slider
        )
        Text(text = "Value: $sliderValue")
    }
}
