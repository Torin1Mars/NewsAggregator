package com.example.newsagregatour.MyScreens

import com.example.newsagregatour.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ripple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun GreetingScreen(navHostController:NavHostController) {
    val modifier = Modifier

    var startAnimation by remember {mutableStateOf(false)}

    val rotationAngle by animateFloatAsState (
        targetValue = if (startAnimation) 360f else 0f, animationSpec = spring(
        dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessVeryLow
        ), label = "rotationAnimation")

    // LaunchedEffect to trigger the animation
    LaunchedEffect(Unit) {
        delay(1000) // Delay before the animation starts
        startAnimation = true
    }

    // LaunchedEffect to trigger the appearing main screen
    LaunchedEffect(Unit) {
        delay(4000) // Delay before the animation starts
        navHostController.navigate(Screens.MainScreen.route) {
            popUpTo(navHostController.graph.id){
                inclusive = true
            }
        }
    }

    fun restartThisScreen():Unit{
        navHostController.navigate(Screens.GreetingsScreen.route){
            popUpTo(navHostController.graph.id){
                inclusive = true
            }
        }
    }

   Box(
        modifier = modifier
            .fillMaxSize()
            .clickable (indication = ripple(),  // Uses new IndicationNodeFactory
                    interactionSource = remember { MutableInteractionSource() },
                onClick = {restartThisScreen()} ),
        contentAlignment = Alignment.Center)
     {
        AnimatedVisibility(
            visible = startAnimation,
            enter = scaleIn(animationSpec = tween(durationMillis = 1000)) )
        {
            Image(painter = painterResource(R.drawable.newsicon),
                contentDescription = stringResource(R.string.StartingPreviewImageDescr),
                modifier = modifier.rotate(rotationAngle))
        }
    }
}