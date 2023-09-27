package com.example.pan.presentation.views.main.edit_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(EditProfileState())
    val state = _state.asStateFlow()

    fun setName(name: String) {
        _state.value.name = name
    }

    fun setEmail(email: String) {
        _state.value.email = email
    }

    fun getUser() = viewModelScope.launch {
        _state.value.getUserResponse = userUseCases.getLoggedUser()
    }
}