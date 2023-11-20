package com.example.pan.presentation.navigation

sealed class Screen(val route: String) {
    data object StartingScreen : Screen("starting_screen")
    data object LoginScreen : Screen("login_screen")
    data object SignupScreen : Screen("signup_screen")
    data object PasswordRecoveryScreen : Screen("password_recovery_screen")
    data object MainPageScreen : Screen("main_page_screen")
    data object LessonPageScreen : Screen("lesson_page_screen")
    data object EditProfileScreen : Screen("edit_profile_screen")
    data object NewLessonScreen : Screen("new_lesson_screen")

//    fun withArgs(vararg parameters: String): String {
//        return buildString {
//            append(route)
//            parameters.forEach { param ->
//                append("/$param")
//            }
//        }
//    }
}
