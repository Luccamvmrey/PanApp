package com.example.pan.presentation.views.signin.password_recovery

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.core.StringConstants.EMAIL
import com.example.pan.core.StringConstants.PASSWORD_RECOVERY_EMAIL_NOT_SENT
import com.example.pan.core.StringConstants.PASSWORD_RECOVERY_EMAIL_SENT
import com.example.pan.core.StringConstants.RECOVER_PASSWORD
import com.example.pan.core.StringConstants.SEND_EMAIL
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.ExtraLargeSpacer
import com.example.pan.presentation.views.components.PanTextField
import com.example.pan.presentation.views.components.ResponseHandler

@Composable
fun PasswordRecoveryScreen(
    viewModel: PasswordRecoveryViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

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
                    text = RECOVER_PASSWORD,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            ExtraLargeSpacer()

            PanTextField(
                value = state.email,
                onValueChange = { viewModel.setEmail(it) },
                labelText = EMAIL,
                error = state.emailError
            )

            ExtraLargeSpacer()

            Button(
                onClick = {
                    if (viewModel.checkFields()) {
                        viewModel.sendPasswordRecoveryEmail()
                    }
                }
            ) {
                Text(text = SEND_EMAIL)
            }
        } else {
            ResponseHandler(
                response = state.passwordRecoveryEmailResponse,
                onSuccess = {
                    Toast.makeText(
                        context,
                        PASSWORD_RECOVERY_EMAIL_SENT, Toast.LENGTH_SHORT
                    ).show()
                },
                onFailure = {
                    Toast.makeText(
                        context,
                        PASSWORD_RECOVERY_EMAIL_NOT_SENT, Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }
}