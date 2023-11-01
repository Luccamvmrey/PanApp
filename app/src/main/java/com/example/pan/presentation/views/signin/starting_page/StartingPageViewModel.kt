package com.example.pan.presentation.views.signin.starting_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.repository.user.SingleUser
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartingPageViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
) : ViewModel() {
    var getUser by mutableStateOf<SingleUser>(Loading)
        private set

    init {
        getLoggedUser()
    }

    private fun getLoggedUser() = viewModelScope.launch {
        getUser = userUseCases.getLoggedUser()
    }
}