package com.example.pan.presentation.views.main.edit_profile.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.*
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.main.edit_profile.EditProfileViewModel

@Composable
fun UpdateUser(
    viewModel: EditProfileViewModel = hiltViewModel(),
    content: () -> Unit
) {
    when(val updateUserResponse = viewModel.updateUser) {
        Loading -> PanProgressBar()
        is Success -> {
            content()
        }
        is Failure -> {
            Text(text = updateUserResponse.e.toString())
        }
        else -> Unit
    }
}