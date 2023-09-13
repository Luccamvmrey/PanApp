package com.example.pan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pan.presentation.views.main.main_page.MainPageScreen
import com.example.pan.presentation.views.signin.login.LoginScreen
import com.example.pan.presentation.views.signin.password_recovery.PasswordRecoveryScreen
import com.example.pan.presentation.views.signin.signup.SignupScreen
import com.example.pan.presentation.views.signin.starting_page.StartingPageScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.StartingScreen.route) {
        composable(route = Screen.StartingScreen.route) {
            StartingPageScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignupScreen.route) {
            SignupScreen(navController = navController)
        }
        composable(route = Screen.PasswordRecoveryScreen.route) {
            PasswordRecoveryScreen()
        }
        composable(route = Screen.MainPageScreen.route) {
            MainPageScreen(navController = navController)
        }

    }
}