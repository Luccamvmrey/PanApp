package com.example.pan.presentation.views.signin.password_recovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.domain.models.InputError
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordRecoveryViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(PasswordRecoveryState())
    val state = _state.asStateFlow()

    fun setEmail(email: String) {
        _state.value.email = email
    }

    fun checkFields(): Boolean {
        _state.value.emailError = InputError()

        if (_state.value.email.isEmpty()) {
            _state.value.emailError = InputError(
                isError = true,
                message = "Campo obrigatório."
            )
        }

        if (_state.value.emailError.isError) {
            return false
        }

        return true
    }

    fun sendPasswordRecoveryEmail() = viewModelScope.launch {
        _state.value.isHandlingResponse = true
        _state.value.passwordRecoveryEmailResponse = userUseCases
            .sendPasswordRecoveryEmail(_state.value.email)
    }
}