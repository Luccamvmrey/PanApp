package com.example.pan.presentation.views.main.new_lesson.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.Failure
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.Response.Success
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.main.new_lesson.NewLessonViewModel

@Composable
fun AddLesson(
    viewModel: NewLessonViewModel = hiltViewModel(),
    onSuccess: () -> Unit
) {
    when(val result = viewModel.addLesson) {
        Loading -> PanProgressBar()
        is Success -> {
            onSuccess()
        }
        is Failure -> {
            Text(text = result.e.toString())
        }
        else -> Unit
    }
}