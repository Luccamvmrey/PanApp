package com.example.pan.presentation.views.signin.signup.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.core.delayNavigation
import com.example.pan.domain.models.Response.Failure
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.Response.Success
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.signin.signup.SignupViewModel

@Composable
fun SignUp(
    viewModel: SignupViewModel = hiltViewModel(),
    navigate: () -> Unit
) {
    when (val createUserResponse = viewModel.createUser) {
        is Loading -> PanProgressBar()
        is Success -> {
            delayNavigation {
                navigate()
            }
        }
        is Failure -> {
            viewModel.handleError(createUserResponse.e)
        }
        else -> {}
    }
}