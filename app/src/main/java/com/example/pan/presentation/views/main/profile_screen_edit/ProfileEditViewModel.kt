package com.example.pan.presentation.views.main.profile_screen_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileEditState())
    val state = _state.asStateFlow()

    fun getUser() = viewModelScope.launch {
        _state.value.getUserResponse = userUseCases.getLoggedUser()
    }

    fun setUser() {

    }

    fun updateUser() {

    }
}