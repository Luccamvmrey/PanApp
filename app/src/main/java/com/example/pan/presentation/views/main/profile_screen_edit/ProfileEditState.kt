package com.example.pan.presentation.views.main.profile_screen_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.user.User

class ProfileEditState {
    // User
    var user by mutableStateOf(User())

    // Responses
    var getUserResponse by mutableStateOf<Response<User>>(Loading)
}