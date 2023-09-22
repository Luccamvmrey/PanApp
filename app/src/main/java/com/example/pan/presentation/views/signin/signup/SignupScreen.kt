package com.example.pan.presentation.views.signin.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.EMAIL
import com.example.pan.core.StringConstants.NAME
import com.example.pan.core.StringConstants.PASSWORD
import com.example.pan.core.StringConstants.SIGNUP
import com.example.pan.core.delayNavigation
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.ExtraLargeSpacer
import com.example.pan.presentation.views.components.LargeSpacer
import com.example.pan.presentation.views.components.PanPasswordTextField
import com.example.pan.presentation.views.components.PanTextField
import com.example.pan.presentation.views.components.ResponseHandler

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
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
                    text = SIGNUP,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            ExtraLargeSpacer()

            PanTextField(
                value = state.name,
                onValueChange = { viewModel.setName(it) },
                labelText = NAME,
                error = state.nameError
            )

            LargeSpacer()

            PanTextField(
                value = state.email,
                onValueChange = { viewModel.setEmail(it) },
                labelText = EMAIL,
                error = state.emailError
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
                        viewModel.createUser()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = SIGNUP)
            }
        } else {
            ResponseHandler(
                response = state.createUserResponse,
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