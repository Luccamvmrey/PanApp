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
fun AddClassToUser(
    viewModel: MainPageViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {
    when(val result = viewModel.updateUser) {
        Loading -> PanProgressBar()
        is Success -> {
            content()
        }
        is Failure -> {
            Text(text = result.e.toString())
        }
        else -> Unit
    }
}