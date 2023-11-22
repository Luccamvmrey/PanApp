package com.example.pan.presentation.views.signin.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.SIGNUP
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.MediumSpacer
import com.example.pan.presentation.views.components.PanText
import com.example.pan.presentation.views.signin.signup.components.SignUp
import com.example.pan.presentation.views.signin.signup.components.SignUpForm

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    navController: NavController
) {
    val scrollState = rememberScrollState()

    ContentHolder(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        PanText(
            text = SIGNUP,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        MediumSpacer()

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