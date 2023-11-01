package com.example.pan.presentation.views.main.my_learning_screen.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.*
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.main.main_page.MainPageViewModel

@Composable
fun GetLessons(
    viewModel: MainPageViewModel = hiltViewModel(),
    lessonsList: (List<Lesson>) -> Unit
) {
    when (val getLessonsResponse = viewModel.getLessons) {
        is Loading -> PanProgressBar()
        is Success -> {
            lessonsList(getLessonsResponse.data)
        }
        is Failure -> {
            Text(text = getLessonsResponse.e.toString())
        }
        else -> {}
    }
}