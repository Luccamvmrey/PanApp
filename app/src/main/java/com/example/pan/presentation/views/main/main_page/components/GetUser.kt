package com.example.pan.presentation.views.main.main_page.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.Response.Success
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.main.main_page.MainPageViewModel

@Composable
fun GetUser(
    viewModel: MainPageViewModel = hiltViewModel(),
    content: @Composable (User) -> Unit
) {
   when (val getUserResponse = viewModel.getUser) {
       is Loading -> PanProgressBar()
       is Success -> {
           content(getUserResponse.data)
       }
       else -> {}
   }
}