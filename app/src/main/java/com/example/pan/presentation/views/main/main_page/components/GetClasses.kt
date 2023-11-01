package com.example.pan.presentation.views.main.main_page.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.Failure
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.Response.Success
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.main.main_page.MainPageViewModel

@Composable
fun GetClasses(
    viewModel: MainPageViewModel = hiltViewModel(),
    classesContent: @Composable (classes: List<PanClass>) -> Unit
) {
    when (val getClassesResponse = viewModel.getClasses) {
        is Loading -> PanProgressBar()
        is Success -> {
            classesContent(getClassesResponse.data)
        }
        is Failure -> {
            Text(
                text = getClassesResponse.e.toString()
            )
        }
        else -> {}
    }
}