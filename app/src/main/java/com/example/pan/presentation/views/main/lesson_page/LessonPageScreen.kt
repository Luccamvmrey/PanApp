package com.example.pan.presentation.views.main.lesson_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.TopBar
import com.example.pan.presentation.views.components.TopBarConfiguration
import com.example.pan.presentation.views.main.lesson_page.components.GetLesson
import com.example.pan.presentation.views.main.lesson_page.components.LessonDisplay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonPageScreen(
    navController: NavController
) {
    GetLesson { lesson ->
        Scaffold(
            topBar = {
                TopBar(configuration = TopBarConfiguration(lesson.lessonTitle!!))
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    ContentHolder(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalPadding = MaterialTheme.spacing.small,
                        horizontalPadding = MaterialTheme.spacing.medium,
                    ) {
                        LessonDisplay(
                            lesson = lesson,
                            navController = navController
                        )
                    }
                }
            }
        )
    }
}