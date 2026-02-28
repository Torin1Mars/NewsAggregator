package com.example.newsagregatour.MyScreens

import android.R.attr.enabled
import com.example.newsagregatour.R
import android.graphics.drawable.PaintDrawable
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun GreetingScreen() {
    val modifier = Modifier

    var startAnimation by remember {mutableStateOf(false)}
    val scaleRatio by animateFloatAsState (
        targetValue = if (startAnimation) 360f else 0f, animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessVeryLow
        ), label = "alphaAnimation")

    //TODO add normal secound animation

    val rotationAngle by animateFloatAsState (
        targetValue = if (startAnimation) 360f else 0f, animationSpec = spring(
        dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessVeryLow
        ), label = "alphaAnimation")

    // Use LaunchedEffect to trigger the animation when the composable enters the composition
    LaunchedEffect(Unit) {
        delay(1000) // Optional: Add a delay before the animation starts
        startAnimation = true
    }

    fun restartAnimation(){

    }

   Box(
        modifier = modifier
            .fillMaxSize()
            .clickable (indication = rememberRipple(),// Uses new IndicationNodeFactory
                    interactionSource = remember { MutableInteractionSource() },
                onClick = {restartAnimation()}),
        contentAlignment = Alignment.Center)
     {
        AnimatedVisibility(
            visible = startAnimation,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000)) +
                    scaleIn(animationSpec = tween(durationMillis = 1000)),
        ) {
            Image(painter = painterResource(R.drawable.newsicon),
                contentDescription = stringResource(R.string.previewImageDescr),
                modifier = modifier.rotate(rotationAngle))
        }
    }
}