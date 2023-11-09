package com.example.pan.presentation.views.main.new_lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.TopBar
import com.example.pan.presentation.views.components.newLessonScreenTopBar
import com.example.pan.presentation.views.main.new_lesson.components.GetClass
import com.example.pan.presentation.views.main.new_lesson.components.GetLessonsList
import com.example.pan.presentation.views.main.new_lesson.components.NewLessonForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewLessonScreen(
    viewModel: NewLessonViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBar(
                extraTitle = " de ${viewModel.panClass.className}",
                configuration = newLessonScreenTopBar
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                ContentHolder(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalPadding = MaterialTheme.spacing.extraLarge,
                    horizontalPadding = MaterialTheme.spacing.large,
                ) {
                    NewLessonForm { newLesson ->
                        viewModel.addLesson(newLesson)
                    }
                }
            }
        }
    )
    GetClass()
    GetLessonsList()
}