package com.example.pan.presentation.views.signin.login.components

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.EMAIL
import com.example.pan.core.StringConstants.FORGOT_PASSWORD
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.core.StringConstants.PASSWORD
import com.example.pan.core.StringConstants.SIGNIN
import com.example.pan.domain.models.InputError
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.components.ExtraLargeSpacer
import com.example.pan.presentation.views.components.ExtraSmallSpacer
import com.example.pan.presentation.views.components.LargeSpacer
import com.example.pan.presentation.views.components.PanPasswordTextField
import com.example.pan.presentation.views.components.PanTextField

@Composable
fun LoginForm(
    navController: NavController,
    emailError: InputError,
    passwordError: InputError,
    onLogin: (String, String) -> Unit
) {
    var email by remember { mutableStateOf(NO_VALUE) }
    var password by remember { mutableStateOf(NO_VALUE) }

    PanTextField(
        value = email,
        onValueChange = { email = it },
        labelText = EMAIL,
        error = emailError,
        keyboardType = KeyboardType.Email
    )

    LargeSpacer()

    PanPasswordTextField(
        value = password,
        onValueChange = { password = it },
        labelText = PASSWORD,
        error = passwordError
    )

    ExtraSmallSpacer()

    TextButton(
        onClick = {
            navController
                .navigate(
                    Screen.PasswordRecoveryScreen.route
                )
        }
    ) {
        Text(
            text = FORGOT_PASSWORD,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }

    ExtraLargeSpacer()

    Button(
        onClick = {
            onLogin(email, password)
        }
    ) {
        Text(text = SIGNIN)
    }
}