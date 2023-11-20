package com.example.pan.presentation.views.main.my_learning_screen.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.delayNavigation
import com.example.pan.domain.models.user.User
import com.example.pan.domain.models.user.getAllowedLessons
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.main.main_page.MainPageViewModel

@Composable
fun MyLearningContent(
    viewModel: MainPageViewModel = hiltViewModel(),
    selectedClassId: String,
    user: User,
    navController: NavController
) {
    if (selectedClassId.isNotEmpty()) {
        viewModel.getLessons(selectedClassId)
        
        GetLessons { lessons ->
            val allowedLessons = when {
                user.teacher!! -> lessons
                else -> user.getAllowedLessons(lessons)
            }.sortedBy { it.order }

            LessonsGrid(
                lessons = allowedLessons,
                onClick = { lessonId ->
                    delayNavigation {
                        navController.navigate(Screen.LessonPageScreen.route + "/$lessonId")
                    }
                }
            )
        }
    } else {
        NoClassSelected()
    }
}


