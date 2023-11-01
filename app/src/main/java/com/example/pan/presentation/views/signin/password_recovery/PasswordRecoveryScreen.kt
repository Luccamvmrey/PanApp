package com.example.pan.presentation.views.signin.password_recovery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.core.StringConstants.RECOVER_PASSWORD
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.ExtraLargeSpacer
import com.example.pan.presentation.views.components.PanText
import com.example.pan.presentation.views.signin.password_recovery.components.SendEmail
import com.example.pan.presentation.views.signin.password_recovery.components.SendEmailForm

@Composable
fun PasswordRecoveryScreen(
    viewModel: PasswordRecoveryViewModel = hiltViewModel()
) {
    ContentHolder(
        verticalPadding = 154.dp,
        verticalArrangement = Arrangement.Top
    ) {
        PanText(
            text = RECOVER_PASSWORD,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        ExtraLargeSpacer()
        SendEmailForm(
            emailError = viewModel.emailError,
            onSendEmail = { email ->
                if (viewModel.checkFields(email)) {
                    viewModel.sendPasswordRecoveryEmail(email)
                }
            }
        )
    }
    SendEmail()
}