package com.example.pan.presentation.views.main.lesson_page.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.*
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.main.lesson_page.LessonPageViewModel

@Composable
fun GetLesson(
    viewModel: LessonPageViewModel = hiltViewModel(),
    onResult: @Composable (Lesson) -> Unit
) {
    when(val result = viewModel.getLesson) {
        Loading -> PanProgressBar()
        is Success -> {
            onResult(result.data)
        }
        is Failure -> {
            Text(text = result.e.toString())
        }
        else -> Unit
    }
}