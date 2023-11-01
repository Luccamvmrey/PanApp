package com.example.pan.presentation.views.signin.password_recovery.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.core.StringConstants.PASSWORD_RECOVERY_EMAIL_NOT_SENT
import com.example.pan.core.StringConstants.PASSWORD_RECOVERY_EMAIL_SENT
import com.example.pan.core.makeToast
import com.example.pan.domain.models.Response
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.signin.password_recovery.PasswordRecoveryViewModel

@Composable
fun SendEmail(
    viewModel: PasswordRecoveryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    when (viewModel.sentEmail) {
        is Response.Loading -> PanProgressBar()
        is Response.Success -> {
            makeToast(
                context,
                PASSWORD_RECOVERY_EMAIL_SENT
            )
        }
        is Response.Failure -> {
            makeToast(
                context,
                PASSWORD_RECOVERY_EMAIL_NOT_SENT
            )
        }
        else -> {}
    }
}