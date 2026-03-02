package com.example.newsagregatour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsagregatour.MyScreens.GreetingScreen
import com.example.newsagregatour.MyScreens.MainScreen
import com.example.newsagregatour.ViewModels.MainViewModel
import com.example.newsagregatour.ui.theme.NewsAgregatourTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Not triggers init block inside View model object
        val mainViewModel : MainViewModel by viewModels()

        setContent {
            val mainNavHostController = rememberNavController()
            NewsAgregatourTheme {
                AppNavigation(mainNavHostController, mainViewModel)
                }
            }
        }
    }

@Composable
fun AppNavigation (navController: NavHostController,myMainViewModel: MainViewModel){

    NavHost(navController = navController,
        startDestination = Screens.GreetingsScreen.route) {

        composable (route = Screens.GreetingsScreen.route){
            GreetingScreen(navController)
        }

        composable(route = Screens.MainScreen.route){
            MainScreen(navController, myMainViewModel)
        }

        composable(route = Screens.SplashScreen.route){
            //SplashScreenLayout()
            }
        }
    }

