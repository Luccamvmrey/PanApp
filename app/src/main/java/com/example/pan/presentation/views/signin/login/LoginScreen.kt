package com.example.pan.presentation.views.signin.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.EMAIL
import com.example.pan.core.StringConstants.PASSWORD
import com.example.pan.core.StringConstants.SIGNIN
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.ExtraLargeSpacer
import com.example.pan.presentation.views.components.LargeSpacer
import com.example.pan.presentation.views.components.PanPasswordTextField
import com.example.pan.presentation.views.components.PanTextField
import com.example.pan.presentation.views.components.ResponseHandler

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value

    ContentHolder {
        if (!state.isHandlingResponse) {
            Row (
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = SIGNIN,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            ExtraLargeSpacer()

            PanTextField(
                value = state.email,
                onValueChange = { viewModel.setEmail(it) },
                labelText = EMAIL,
                error = state.emailError,
                keyboardType = KeyboardType.Email
            )

            LargeSpacer()

            PanPasswordTextField(
                value = state.password,
                onValueChange = { viewModel.setPassword(it) },
                labelText = PASSWORD,
                error = state.passwordError
            )

            ExtraLargeSpacer()

            Button(
                onClick = {
                    if (viewModel.checkFields()) {
                        viewModel.logUser()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = SIGNIN)
            }
        } else {
            ResponseHandler (
                response = state.logUserResponse,
                onSuccess = {
                    // TODO: Navigate to main page with userID
                },
                onFailure = { error ->
                    viewModel.handleError(error)
                }
            )
        }
    }
}