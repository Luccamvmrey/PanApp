package com.example.pan.presentation.views.main.edit_profile.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.Response.Failure
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.Response.Success
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.main.edit_profile.EditProfileViewModel

@Composable
fun UploadImage(
    viewModel: EditProfileViewModel = hiltViewModel(),
    content: (String) -> Unit
) {
    when(val uploadImageResponse = viewModel.uploadImage) {
        Loading -> PanProgressBar()
        is Success -> {
            content(uploadImageResponse.data)
        }
        is Failure -> {
            Text(text = uploadImageResponse.e.toString())
        }
        else -> Unit
    }
}