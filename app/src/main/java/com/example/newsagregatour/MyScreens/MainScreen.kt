package com.example.newsagregatour.MyScreens

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsagregatour.R
import com.example.newsagregatour.ViewModels.MainViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList

import androidx.compose.ui.draw.clip

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import coil3.compose.AsyncImage
import com.example.newsagregatour.Retrofit.Article

import com.example.newsagregatour.data.NewsItem
import com.example.newsagregatour.ui.theme.ScrollThumbSettings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.count

import kotlin.random.Random
import my.nanihadesuka.compose.LazyColumnScrollbar
import org.intellij.lang.annotations.JdkConstants


@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun MainScreen(navHostController: NavHostController){
    val modifier = Modifier
    val context: Context = LocalContext.current

    //Not triggers init block inside View model object
    val mainViewModel : MainViewModel = hiltViewModel()

    var showBottomSplashScreen = remember {mutableStateOf<Boolean>(false)}
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
                bottomBar = {StaticBottomBar(modifier, {mainViewModel.loadNewNews()},  {showBottomSplashScreen()})} )
            {
                if (showBottomSplashScreen.value){
                    BottomSplashScreen(context, modifier, {hideBottomSplashScreen()}, mainViewModel)
                }
                Column (modifier = modifier.fillMaxSize()){
                    //Categories
                    ChoosenCategoryes(modifier, mainViewModel, {showBottomSplashScreen()})

                    CategoriesStatusBar(modifier, mainViewModel.currentCategory.value, mainViewModel.newsCount)

                    //Trending in this category
                    TrendingNews(modifier, mainViewModel.allNewsList)

                    //Main Field
                    MainNewsList(modifier, context, mainViewModel.allNewsList)
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

        IconButton(onClick = showBottomSplashScreen){
            Icon(imageVector = Icons.Default.Menu,
                contentDescription = "Menu")
        }
    }
}

@Composable
fun ChoosenCategoryes(modifier: Modifier, viewModel: MainViewModel, showBottomSplashScreen: () -> Unit)
{
    val myCategories  = viewModel.myCategories
    val scrollState = rememberScrollState()

    fun generateRandomColor(): Color {
        return Color(
            red = Random.nextFloat(),
            green = Random.nextFloat(),
            blue = Random.nextFloat(),
            alpha = 0.6f
        )
    }

    Row(modifier = Modifier.fillMaxWidth()
        .horizontalScroll(scrollState)
        .padding(top = 80.dp)
        .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically)
    {
        // First add button
        FilledTonalButton(
            modifier = modifier.padding(start = 10.dp),
            onClick = showBottomSplashScreen,
            contentPadding = ButtonDefaults.TextButtonContentPadding,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green.copy(alpha = 0.8F))
        )
        {
            Image(Icons.Default.Add, stringResource(R.string.AddButton))
        }

        val currentCategory = viewModel.currentCategory

        myCategories.forEach { instance ->
            FilledTonalButton(
                modifier = modifier.padding(start = 10.dp),
                onClick = {viewModel.loadNewCategory(instance)},
                contentPadding = ButtonDefaults.TextButtonContentPadding,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = remember {generateRandomColor()} )
            )
            {
                Text(text = instance.uppercase(), fontSize = 14.sp, color = Color.Black,
                    fontWeight = if (instance.lowercase() == currentCategory.value.lowercase()){
                        FontWeight.Bold} else {FontWeight.Light})
            }
        }
    }
}

@Composable
fun CategoriesStatusBar(modifier: Modifier, currentCategory: String, newsListCount: Int){

    Row(modifier = modifier.fillMaxWidth().padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically)
    {

        Column {
            Text(text = stringResource(R.string.CurrentCategory)+ " :",
                modifier = modifier,
                fontSize = 16.sp)

            Text(text = currentCategory,
                modifier = modifier,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold)
        }

        Text(text = stringResource(R.string.NewsCount) + " :" + newsListCount,
            modifier = modifier,
            fontSize = 16.sp)
    }
}

@Composable
fun TrendingNews (modifier: Modifier, newsData: Flow<List<NewsItem>>, goSingleScreen:(Article)-> Unit){
    //TODO add go to single screen transfer
    val myNewsData by newsData.collectAsStateWithLifecycle(initialValue = emptyList<NewsItem>())

    LazyHorizontalGrid(modifier = modifier.padding(top = 15.dp)
        .padding(start = 20.dp)
        .fillMaxWidth()
        .fillMaxHeight(0.2F),
        userScrollEnabled = true,
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(5.dp))
    {
        items (count = myNewsData.size, key =  {myNewsData[it].newsId}) {

            AsyncImage(
                model = myNewsData[it].newsBody.image_url,
                contentDescription = "${myNewsData[it].newsTitle} preview image",
                contentScale = ContentScale.Fit,
                modifier = modifier.clickable {Log.d("MyTag", myNewsData[it].newsTitle)}
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
            /*Surface (modifier = modifier, shape = RoundedCornerShape(5.dp),
                color = Color.White.copy(alpha = 0.5F)){

                Row(modifier = modifier,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {

                    Image(painterResource(R.drawable.temporarynews),
                        contentDescription = stringResource(R.string.TempNews))

                    Text(modifier = modifier.padding(start = 12.dp),
                            text = "Example",
                        fontSize = 12.sp)
                }
            }*/
        }
    }
}

@Composable
fun MainNewsList(modifier: Modifier, context: Context, newsList: Flow<List<NewsItem>>){
    val thisModifier = modifier
    val thisContext = context

    @OptIn(kotlinx. serialization. ExperimentalSerializationApi::class)
    val myNews by newsList.collectAsStateWithLifecycle(initialValue = emptyList())

    val listState = rememberLazyListState()

    if (myNews.isNotEmpty()){
        LazyColumnScrollbar(state = listState,
            settings = ScrollThumbSettings,
            modifier = thisModifier.fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
        ){
            LazyColumn(
                state = listState,
                modifier = thisModifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)) {
                items (count = myNews.size, key = {myNews[it].newsId}) {

                    Surface(modifier = thisModifier.fillMaxSize()
                        .clickable {},//showInSingleScreen*/},
                        shape = RoundedCornerShape(5.dp),
                        color = Color.White.copy(0.5F)) {

                        Column(modifier = thisModifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally)
                        {
                            val previewUrl = myNews[it].newsBody.image_url

                            if (previewUrl != null){
                                AsyncImage(model = previewUrl,
                                    contentDescription = "${myNews[it].newsTitle} preview image",
                                    modifier = thisModifier.fillMaxWidth()
                                        .padding(5.dp)
                                        .clip(RoundedCornerShape(5.dp)),
                                    contentScale = ContentScale.FillWidth)

                            }else{
                                AsyncImage(model = R.drawable.temporaryimage,
                                    contentDescription = "${myNews[it].newsTitle} preview image",
                                    modifier = thisModifier.size(100.dp)
                                        .padding(vertical = 20.dp),
                                    alpha = 0.5F)
                            }

                            //Title
                            @OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
                            Text(text = myNews[it].newsTitle,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold)

                            //Short Description
                            @OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
                            Text(text = myNews[it].newsBody.description!!,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Light)
                        }
                    }
                }
            }
        }
    }
    else{
        Box(modifier = thisModifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Text(text = stringResource(R.string.UnavailableData),
                fontSize = 18.sp,
                fontWeight = FontWeight.Light)
        }
    }
}




