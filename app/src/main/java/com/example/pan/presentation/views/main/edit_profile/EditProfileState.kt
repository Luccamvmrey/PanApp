package com.example.pan.presentation.views.main.edit_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.user.User

class EditProfileState {
    // Variables
    var name by mutableStateOf("")
    var email by mutableStateOf("")

    // Errors
    var nameError by mutableStateOf(InputError())
    var emailError by mutableStateOf(InputError())

    // Responses
    var getUserResponse by mutableStateOf<Response<User>>(Loading)
}