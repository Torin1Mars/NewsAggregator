package com.example.newsagregatour.MyScreens

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
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
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsagregatour.R
import com.example.newsagregatour.ViewModels.MainViewModel
import kotlinx.coroutines.launch
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlin.random.Random


@Composable
fun MainScreen(navHostController: NavHostController){
    val modifier = Modifier

    //Not triggers init block inside View model object
    val mainViewModel : MainViewModel = hiltViewModel()

    var showBottomSplashScreen = remember { mutableStateOf<Boolean>(false) }
    val gradientColors = listOf(Color(0xFF4CAF50), Color(0xFF8BC34A), Color(0xFFCDDC39))


    fun showBottomSplashScreen(){
        showBottomSplashScreen.value = true
    }

    fun hideBottomSplashScreen(){
        showBottomSplashScreen.value = false
    }

    fun refreshDb (/*NewSearchingInput: Map<String, List<String>>*/): Unit{
        //TODO

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
                bottomBar = {StaticBottomBar(modifier, {refreshDb()},  {showBottomSplashScreen()})} )
            {
                if (showBottomSplashScreen.value){
                    BottomSplashScreen(modifier, {hideBottomSplashScreen()})
                }
                Box(modifier = modifier.fillMaxSize()){
                    ChoosenCategoryes(modifier, emptyList())
                }

            }
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

        Spacer(modifier=modifier.width(10.dp))

        Text(stringResource(R.string.App_MainScreen_Title),
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp)
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
fun ChoosenCategoryes(modifier: Modifier, myCategories : List<String>){

    val myCategories : List<String> = remember{myCategories.toMutableStateList()}

    val tempData = remember {mutableListOf<String>("Sport", "Politics", "Interesting", "Football")}

    fun generateRandomColor(): Color {
        return Color(
            red = Random.nextFloat(),
            green = Random.nextFloat(),
            blue = Random.nextFloat(),
            alpha = 0.6f
        )
    }

    fun getBgColors(): List<Color>{
        val myColors = mutableListOf<Color>()
        tempData.forEach { myColors.add(generateRandomColor())}

        return myColors
    }

    val bgButtonsColors = rememberSaveable { getBgColors()}

    LazyRow(modifier = Modifier.fillMaxWidth()
        .padding(top = 80.dp)
        .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically)
    {
        items(count = tempData.size, key = {tempData[it].lowercase()}  ) { it ->
            FilledTonalButton(
                modifier = modifier.padding(start = 10.dp),
                onClick = {},
                contentPadding = ButtonDefaults.TextButtonContentPadding,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = bgButtonsColors[it])
            )
            {
                Text(text = tempData[it].uppercase(), fontSize = 14.sp, color = Color.Black)
            }
        }
    }
}
