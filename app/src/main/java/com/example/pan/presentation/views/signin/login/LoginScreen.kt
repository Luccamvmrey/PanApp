package com.example.pan.presentation.views.signin.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.SIGNIN
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.ExtraLargeSpacer
import com.example.pan.presentation.views.components.PanText
import com.example.pan.presentation.views.signin.login.components.LogUser
import com.example.pan.presentation.views.signin.login.components.LoginForm

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    ContentHolder(
        verticalPadding = 154.dp,
        verticalArrangement = Arrangement.Top
    ) {
        PanText(
            text = SIGNIN,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )

        ExtraLargeSpacer()

        LoginForm(
            navController = navController,
            emailError = viewModel.emailError,
            passwordError = viewModel.passwordError,
            onLogin = { email, password ->
                if (
                    viewModel.checkFields(
                        email, password
                    )
                ) {
                    viewModel.logUser(
                        email, password
                    )
                }
            }
        )
    }
    LogUser {
        navController.navigate(
            Screen.MainPageScreen.route
        )
    }
}