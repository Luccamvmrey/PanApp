package com.example.pan.presentation.views.signin.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.core.StringConstants.EMAIL_ALREADY_IN_USE
import com.example.pan.core.StringConstants.INVALID_EMAIL
import com.example.pan.core.StringConstants.INVALID_PASSWORD
import com.example.pan.core.StringConstants.PASSWORD_TOO_SHORT
import com.example.pan.core.StringConstants.REQUIRED_FIELD
import com.example.pan.domain.models.InputError
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    fun setName(name: String) {
        _state.value.name = name
    }

    fun setEmail(email: String) {
        _state.value.email = email
    }

    fun setPassword(password: String) {
        _state.value.password = password
    }

    fun setIsTeacher(isTeacher: Boolean) {
        _state.value.isTeacher = isTeacher
    }

    fun setIsExpanded(isExpanded: Boolean) {
        _state.value.isDropdownMenuExpanded = isExpanded
    }

    fun checkFields() : Boolean {
        _state.value.nameError = InputError()
        _state.value.emailError = InputError()
        _state.value.passwordError = InputError()

        if (_state.value.name.isEmpty()) {
            _state.value.nameError = InputError(
                isError = true,
                message = REQUIRED_FIELD
            )
        }

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

        if (
            _state.value.nameError.isError ||
            _state.value.emailError.isError ||
            _state.value.passwordError.isError
        ) {
            return false
        }

        return true
    }

    fun handleError(
        e: Exception?
    ) {
        val error = e.toString()
        _state.value.isHandlingResponse = false

        if (error.contains("email")) {
            if (error.contains("The email address is already in use by another account.")) {
                _state.value.emailError = InputError(
                    isError = true,
                    message = EMAIL_ALREADY_IN_USE
                )
            } else {
                _state.value.emailError = InputError(
                    isError = true,
                    message = INVALID_EMAIL
                )
            }
        }

        if (error.contains("password")) {
            if (
                error.contains("The given password is invalid." +
                        " [ Password should be at least 6 characters ]")
            ) {
                _state.value.passwordError = InputError(
                    isError = true,
                    message = PASSWORD_TOO_SHORT
                )
            } else {
                _state.value.passwordError = InputError(
                    isError = true,
                    message = INVALID_PASSWORD
                )
            }
        }
    }

    fun createUser() = viewModelScope.launch {
        _state.value.isHandlingResponse = true
        _state.value.createUserResponse = userUseCases.createUser(
            name = _state.value.name,
            email = _state.value.email,
            password = _state.value.password
        )
    }
}