package com.example.pan.presentation.views.main.edit_profile.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.Failure
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.Response.Success
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.main.edit_profile.EditProfileViewModel

@Composable
fun GetUser(
    viewModel: EditProfileViewModel = hiltViewModel(),
    content: @Composable (User) -> Unit
) {
    when (val getUserResponse = viewModel.getUser) {
        Loading -> PanProgressBar()
        is Success -> {
            content(getUserResponse.data)
        }
        is Failure -> {
            Text(text = getUserResponse.e.toString())
        }
        else -> Unit
    }
}