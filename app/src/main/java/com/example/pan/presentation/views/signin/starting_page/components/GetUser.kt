package com.example.pan.presentation.views.signin.starting_page.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.Success
import com.example.pan.presentation.views.signin.starting_page.StartingPageViewModel

@Composable
fun GetUser(
    viewModel: StartingPageViewModel = hiltViewModel(),
    navigateToMainPage: () -> Unit
) {
    when (viewModel.getUser) {
        is Success -> navigateToMainPage()
        else -> {}
    }
}