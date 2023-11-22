package com.example.pan.presentation.views.signin.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.core.StringConstants.EMAIL_ALREADY_IN_USE
import com.example.pan.core.StringConstants.INVALID_EMAIL
import com.example.pan.core.StringConstants.INVALID_PASSWORD
import com.example.pan.core.StringConstants.PASSWORD_TOO_SHORT
import com.example.pan.core.StringConstants.REQUIRED_FIELD
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.Response.Idle
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.repository.user.CreateUserResponse
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  SignupViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    var nameError by mutableStateOf(InputError())
    var emailError by mutableStateOf(InputError())
    var passwordError by mutableStateOf(InputError())

    var createUser by mutableStateOf<CreateUserResponse>(Idle)
        private set

    fun checkFields(name: String, email: String, password: String): Boolean {
        nameError = InputError()
        emailError = InputError()
        passwordError = InputError()

        if (name.isEmpty()) {
            nameError = InputError(
                isError = true,
                message = REQUIRED_FIELD
            )
        }

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

        if (
            nameError.isError ||
            emailError.isError ||
            passwordError.isError
        ) {
            return false
        }

        return true
    }

    fun handleError(
        e: Exception?
    ) {
        val error = e.toString()
        createUser = Idle

        if (error.contains("email")) {
            emailError =
                if (error.contains("The email address is already in use by another account.")) {
                    InputError(
                        isError = true,
                        message = EMAIL_ALREADY_IN_USE
                    )
                } else {
                    InputError(
                        isError = true,
                        message = INVALID_EMAIL
                    )
                }
        }

        if (error.contains("password")) {
            passwordError = if (
                error.contains(
                    "The given password is invalid." +
                            " [ Password should be at least 6 characters ]"
                )
            ) {
                InputError(
                    isError = true,
                    message = PASSWORD_TOO_SHORT
                )
            } else {
                InputError(
                    isError = true,
                    message = INVALID_PASSWORD
                )
            }
        }
    }

    fun createUser(
        name: String, email: String,
        password: String, isTeacher: Boolean
    ) = viewModelScope.launch {
        createUser = Loading
        createUser = userUseCases.createUser(
            name = name,
            email = email,
            password = password,
            isTeacher = isTeacher
        )
    }
}