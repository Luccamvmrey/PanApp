package com.example.pan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pan.presentation.views.signin.starting_page.StartingPageScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.StartingScreen.route) {
        composable(route = Screen.StartingScreen.route) {
            StartingPageScreen(navController = navController)
        }
    }
}