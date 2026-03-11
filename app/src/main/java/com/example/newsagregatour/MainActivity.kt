package com.example.newsagregatour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsagregatour.MyScreens.GreetingScreen
import com.example.newsagregatour.MyScreens.MainScreen
import com.example.newsagregatour.MyScreens.Screens
import com.example.newsagregatour.ui.theme.NewsAgregatourTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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

        composable(route = Screens.SingleNewsScreen.route){
            //SingleNewsScreen()
            }
        }
    }

