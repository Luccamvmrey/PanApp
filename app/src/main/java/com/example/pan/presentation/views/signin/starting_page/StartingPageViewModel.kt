package com.example.pan.presentation.views.signin.starting_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartingPageViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(StartingPageState())
    val state = _state.asStateFlow()

    fun setIsHandlingState(isHandlingResponse: Boolean) {
        _state.value.isHandlingResponse = isHandlingResponse
    }

    fun getLoggedUser() = viewModelScope.launch {
        _state.value.isHandlingResponse = true
        _state.value.getUserResponse = userUseCases.getLoggedUser()
    }
}