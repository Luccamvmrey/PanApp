package com.example.pan.presentation.views.main.main_page.components

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.views.main.my_learning_screen.MyLearningScreen
import com.example.pan.presentation.views.main.profile_screen.ProfileScreen

@Composable
fun MainPageContent(
    selectedScreenIndex: Int,
    user: User
) {
    when (selectedScreenIndex) {
        0 -> {
            ProfileScreen(user = user)
        }
        1 -> {
            // My Learning
            GetClasses { classes ->
                Log.d("ADD_STUDENT", "MainPageContent: $classes")
                MyLearningScreen(
                    user = user,
                    classesList = classes
                )
            }
        }
    }
}