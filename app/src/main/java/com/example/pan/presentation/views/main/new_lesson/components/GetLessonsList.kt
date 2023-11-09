package com.example.pan.presentation.views.main.new_lesson.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.Failure
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.Response.Success
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.main.new_lesson.NewLessonViewModel

@Composable
fun GetLessonsList(
    viewModel: NewLessonViewModel = hiltViewModel()
) {
    when (val result = viewModel.getLessonsList) {
        Loading -> PanProgressBar()
        is Success -> {
            viewModel.lessonsList = result.data
        }
        is Failure -> {
            print(result.e)
        }
        else -> Unit
    }
}