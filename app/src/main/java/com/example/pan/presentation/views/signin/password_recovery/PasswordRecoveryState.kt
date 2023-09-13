package com.example.pan.presentation.views.signin.password_recovery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Loading

class PasswordRecoveryState {
    // Variables
    var email by mutableStateOf(NO_VALUE)

    // Errors
    var emailError by mutableStateOf(InputError())

    // Booleans
    var isHandlingResponse by mutableStateOf(false)

    // Response
    var passwordRecoveryEmailResponse by mutableStateOf<Response<Boolean>>(Loading)
}