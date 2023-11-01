package com.example.pan.presentation.views.signin.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.SIGNUP
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.LargeSpacer
import com.example.pan.presentation.views.components.PanText
import com.example.pan.presentation.views.signin.signup.components.SignUp
import com.example.pan.presentation.views.signin.signup.components.SignUpForm

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    navController: NavController
) {
    ContentHolder(
        verticalPadding = 154.dp,
        verticalArrangement = Arrangement.Top
    ) {
        PanText(
            text = SIGNUP,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        LargeSpacer()

        SignUpForm(
            nameError = viewModel.nameError,
            emailError = viewModel.emailError,
            passwordError = viewModel.passwordError,
            onSignup = { user, password ->
                if (
                    viewModel.checkFields(
                        user.name!!, user.email!!, password
                    )
                ) {
                    viewModel.createUser(
                        user.name, user.email,
                        password, user.teacher!!
                    )
                }
            }
        )
    }
    SignUp {
        navController.navigate(
            Screen.MainPageScreen.route
        )
    }
}