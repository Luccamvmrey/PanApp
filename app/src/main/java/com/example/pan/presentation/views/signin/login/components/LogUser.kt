package com.example.pan.presentation.views.signin.login.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.core.delayNavigation
import com.example.pan.domain.models.Response
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.signin.login.LoginViewModel

@Composable
fun LogUser(
    viewModel: LoginViewModel = hiltViewModel(),
    navigate: () -> Unit
) {
    when (val logUserResponse = viewModel.logUser) {
        is Response.Loading -> PanProgressBar()
        is Response.Success -> {
            delayNavigation {
                navigate()
            }
        }
        is Response.Failure -> {
            viewModel.handleError(
                logUserResponse.e
            )
        }
        else -> {}
    }
}