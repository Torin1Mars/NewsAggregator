package com.example.newsagregatour

import kotlinx.serialization.Serializable

@Serializable
enum class Screens(val route: String) {
    GreetingsScreen("GreetingsScreen"),
    MainScreen("MainScreen"),
    SplashScreen("SplashScreen")
}