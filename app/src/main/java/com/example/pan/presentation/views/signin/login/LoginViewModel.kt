package com.example.pan.presentation.views.signin.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.core.StringConstants.EMAIL_NOT_FOUND
import com.example.pan.core.StringConstants.INVALID_EMAIL
import com.example.pan.core.StringConstants.INVALID_PASSWORD
import com.example.pan.core.StringConstants.REQUIRED_FIELD
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.Response.Idle
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.repository.user.LogUserResponse
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    var emailError by mutableStateOf(InputError())
    var passwordError by mutableStateOf(InputError())

    var logUser by mutableStateOf<LogUserResponse>(Idle)
        private set

    fun checkFields(email: String, password: String): Boolean {
        emailError = InputError()
        passwordError = InputError()

        if (email.isEmpty()) {
            emailError = InputError(
                isError = true,
                message = REQUIRED_FIELD
            )
        }

        if (password.isEmpty()) {
            passwordError = InputError(
                isError = true,
                message = REQUIRED_FIELD
            )
        }

        if (emailError.isError || passwordError.isError) {
            return false
        }

        return true
    }

    fun handleError(
        e: Exception?
    ) {
        val error = e.toString()
        logUser = Idle

        if (error.contains("record") || error.contains("email")) {
            emailError = InputError(
                isError = true,
                message = if (
                    error.contains("There is no user record corresponding to this identifier.")
                ) {
                    EMAIL_NOT_FOUND
                } else {
                    INVALID_EMAIL
                }
            )
        }

        if (error.contains("password")) {
            passwordError = InputError(
                isError = true,
                message = INVALID_PASSWORD
            )
        }
    }

    fun logUser(email: String, password: String) = viewModelScope.launch {
        logUser = Loading
        logUser = userUseCases.logUser(
            email = email,
            password = password
        )
    }
}