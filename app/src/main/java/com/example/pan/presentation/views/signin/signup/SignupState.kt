package com.example.pan.presentation.views.signin.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Loading

class SignupState {
    // Variables
    var name by mutableStateOf(NO_VALUE)
    var email by mutableStateOf(NO_VALUE)
    var password by mutableStateOf(NO_VALUE)

    // Errors
    var nameError by mutableStateOf(InputError())
    var emailError by mutableStateOf(InputError())
    var passwordError by mutableStateOf(InputError())

    // Boolean
    var isHandlingResponse by mutableStateOf(false)
    var isTeacher by mutableStateOf(false)
    var isDropdownMenuExpanded by mutableStateOf(false)

    // Responses
    var createUserResponse by mutableStateOf<Response<Boolean>>(Loading)
}