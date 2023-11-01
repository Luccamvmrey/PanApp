package com.example.pan.presentation.views.signin.password_recovery.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import com.example.pan.core.StringConstants.EMAIL
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.core.StringConstants.SEND_EMAIL
import com.example.pan.domain.models.InputError
import com.example.pan.presentation.views.components.ExtraLargeSpacer
import com.example.pan.presentation.views.components.PanTextField

@Composable
fun SendEmailForm(
    emailError: InputError,
    onSendEmail: (String) -> Unit
) {
    var email by remember { mutableStateOf(NO_VALUE) }

    PanTextField(
        value = email,
        onValueChange = { email = it },
        labelText = EMAIL,
        error = emailError,
        keyboardType = KeyboardType.Email
    )

    ExtraLargeSpacer()

    Button(
        onClick = {
            onSendEmail(email)
        }
    ) {
        Text(text = SEND_EMAIL)
    }
}