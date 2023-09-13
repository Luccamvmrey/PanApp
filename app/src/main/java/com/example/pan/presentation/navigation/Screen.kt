package com.example.pan.presentation.navigation

sealed class Screen(val route: String) {
    data object StartingScreen : Screen("starting_screen")
    data object LoginScreen : Screen("login_screen")
    data object SignupScreen : Screen("signup_screen")

    fun withArgs(vararg parameters: String): String {
        return buildString {
            append(route)
            parameters.forEach { param ->
                append("/$param")
            }
        }
    }
}
