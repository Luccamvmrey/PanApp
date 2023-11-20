package com.example.pan.presentation.views.main.lesson_page.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.Failure
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.Response.Success
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.main.lesson_page.LessonPageViewModel

@Composable
fun UpdateUser(
    viewModel: LessonPageViewModel = hiltViewModel(),
    onFinished: () -> Unit
) {
    when (val result = viewModel.updateUser) {
        Loading -> PanProgressBar()
        is Success -> {
            onFinished()
        }
        is Failure -> {
            Text(text = result.e.toString())
        }
        else -> Unit
    }
}