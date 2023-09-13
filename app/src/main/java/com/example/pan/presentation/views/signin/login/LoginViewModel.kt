package com.example.pan.presentation.views.signin.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.core.StringConstants.EMAIL_NOT_FOUND
import com.example.pan.core.StringConstants.INVALID_EMAIL
import com.example.pan.core.StringConstants.INVALID_PASSWORD
import com.example.pan.core.StringConstants.REQUIRED_FIELD
import com.example.pan.domain.models.InputError
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun setEmail(email: String) {
        _state.value.email = email
    }

    fun setPassword(password: String) {
        _state.value.password = password
    }

    fun checkFields() : Boolean {
        _state.value.emailError = InputError()
        _state.value.passwordError = InputError()

        if (_state.value.email.isEmpty()) {
            _state.value.emailError = InputError(
                isError = true,
                message = REQUIRED_FIELD
            )
        }

        if (_state.value.password.isEmpty()) {
            _state.value.passwordError = InputError(
                isError = true,
                message = REQUIRED_FIELD
            )
        }

        if (_state.value.emailError.isError || _state.value.passwordError.isError) {
            return false
        }

        return true
    }

    fun handleError(
        e: Exception?
    ) {
        val error = e.toString()
        _state.value.isHandlingResponse = false

        if (error.contains("record") || error.contains("email")) {
            _state.value.emailError = InputError(
                isError = true,
                message = if (error.contains("There is no user record corresponding to this identifier.")) {
                    EMAIL_NOT_FOUND
                } else {
                    INVALID_EMAIL
                }
            )
        }

        if (error.contains("password")) {
            _state.value.passwordError = InputError(
                isError = true,
                message = INVALID_PASSWORD
            )
        }
    }

    fun logUser() = viewModelScope.launch {
        _state.value.isHandlingResponse = true
        _state.value.logUserResponse = userUseCases.logUser(
            email = _state.value.email,
            password = _state.value.password
        )
    }
}