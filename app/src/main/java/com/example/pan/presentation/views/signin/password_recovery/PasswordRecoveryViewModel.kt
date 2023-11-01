package com.example.pan.presentation.views.signin.password_recovery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Idle
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordRecoveryViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    var emailError = InputError()

    var sentEmail by mutableStateOf<Response<Boolean>>(Idle)
        private set

    fun checkFields(email: String): Boolean {
        emailError = InputError()

        if (email.isEmpty()) {
            emailError = InputError(
                isError = true,
                message = "Campo obrigatório."
            )
        }

        if (emailError.isError) {
            return false
        }

        return true
    }

    fun sendPasswordRecoveryEmail(email: String) = viewModelScope.launch {
        sentEmail = Loading
        sentEmail = userUseCases
            .sendPasswordRecoveryEmail(email)
    }
}