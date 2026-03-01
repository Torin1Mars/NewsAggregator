package com.example.newsagregatour

import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsagregatour.MyScreens.GreetingScreen
import com.example.newsagregatour.MyScreens.MainScreen
import com.example.newsagregatour.ViewModels.MainViewModel
import com.example.newsagregatour.data.NewsItem
import com.example.newsagregatour.domain.AppDatabase
import com.example.newsagregatour.ui.theme.NewsAgregatourTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.compose

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Not triggers init block inside View model object
        //val myMainViewModel : MainViewModel by viewModels()

        setContent {
            val mainNavHostController = rememberNavController()
            NewsAgregatourTheme {
                AppNavigation(mainNavHostController)
                }
            }
        }
    }

@Composable
fun AppNavigation (navController: NavHostController){

    NavHost(navController = navController,
        startDestination = Screens.GreetingsScreen.route) {

        composable (route = Screens.GreetingsScreen.route){
            GreetingScreen(navController)
        }

        composable(route = Screens.MainScreen.route){
            MainScreen(navController)
        }

        composable(route = Screens.SplashScreen.route){
            //SplashScreenLayout()
            }
        }
    }

