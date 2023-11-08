package com.example.pan.presentation.views.main.my_learning_screen.components.new_class_dialog

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.Failure
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.Response.Success
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.main.main_page.MainPageViewModel

@Composable
fun AddStudent(
    viewModel: MainPageViewModel = hiltViewModel(),
    content: @Composable (Boolean) -> Unit
) {
    when(val result = viewModel.addStudent) {
        Loading -> PanProgressBar()
        is Success -> {
            content(result.data)
        }
        is Failure -> {
            Text(text = result.e.toString())
        }
        else -> Unit
    }
}