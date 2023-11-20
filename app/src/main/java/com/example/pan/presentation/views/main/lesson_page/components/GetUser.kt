package com.example.pan.presentation.views.main.lesson_page.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.Success
import com.example.pan.presentation.views.main.lesson_page.LessonPageViewModel

@Composable
fun GetUser(
    viewModel: LessonPageViewModel = hiltViewModel()
) {
    when(val result = viewModel.getUser) {
        is Success -> {
            viewModel.loggedUser = result.data
        }
        else -> Unit
    }
}