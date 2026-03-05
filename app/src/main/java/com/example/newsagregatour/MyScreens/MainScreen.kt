package com.example.newsagregatour.MyScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsagregatour.R
import com.example.newsagregatour.ViewModels.MainViewModel
import kotlinx.coroutines.launch


@Composable
fun MainScreen(navHostController: NavHostController, mainViewModel: MainViewModel){
    val modifier = Modifier

    var showBottomSplashScreen = remember { mutableStateOf<Boolean>(false) }

    val gradientColors = listOf(Color(0xFF4CAF50), Color(0xFF8BC34A), Color(0xFFCDDC39))


    fun showBottomSplashScreen(){
        showBottomSplashScreen.value = true
    }

    fun hideBottomSplashScreen(){
        showBottomSplashScreen.value = false
    }

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
                bottomBar = {StaticBottomBar(modifier, {},  {showBottomSplashScreen()})} )
            {
                if (showBottomSplashScreen.value){
                    bottomSplashScreen(modifier, {hideBottomSplashScreen()})

                }

            }
        }
    }
}

@Composable
fun StaticBottomBar(modifier: Modifier,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bottomSplashScreen(modifier: Modifier, hideBottomSplashScreen: ()-> Unit){

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp),

        onDismissRequest = {scope.launch { sheetState.hide()}
            .invokeOnCompletion {hideBottomSplashScreen()}},

        dragHandle = null,
        content = {
            Box(modifier = modifier.fillMaxWidth().height(300.dp),
                contentAlignment = Alignment.Center){
                Text(text =  "This is Example", fontSize = 20.sp)

            }

        }
    )
}
