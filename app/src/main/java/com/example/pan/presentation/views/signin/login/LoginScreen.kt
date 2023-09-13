package com.example.pan.presentation.views.signin.login

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.EMAIL
import com.example.pan.core.StringConstants.FORGOT_PASSWORD
import com.example.pan.core.StringConstants.PASSWORD
import com.example.pan.core.StringConstants.SIGNIN
import com.example.pan.core.delayNavigation
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.ExtraLargeSpacer
import com.example.pan.presentation.views.components.ExtraSmallSpacer
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

    ContentHolder (
        verticalPadding = 154.dp,
        verticalArrangement = Arrangement.Top
    ) {
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

            ExtraSmallSpacer()

            Text(
                text = FORGOT_PASSWORD,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.PasswordRecoveryScreen.route)
                }
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
                    delayNavigation {
                        navController.navigate(Screen.MainPageScreen.route)
                    }
                },
                onFailure = { error ->
                    viewModel.handleError(error)
                }
            )
        }
    }
}