package com.example.pan.presentation.views.signin.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Loading

class LoginState {
    // Variables
    var email by mutableStateOf(NO_VALUE)
    var password by mutableStateOf(NO_VALUE)

    // Errors
    var emailError by mutableStateOf(InputError())
    var passwordError by mutableStateOf(InputError())

    // Boolean
    var isHandlingResponse by mutableStateOf(false)

    // Responses
    var logUserResponse by mutableStateOf<Response<Boolean>>(Loading)
}