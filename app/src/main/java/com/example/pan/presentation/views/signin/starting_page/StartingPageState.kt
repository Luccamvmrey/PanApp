package com.example.pan.presentation.views.signin.starting_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.user.User

class StartingPageState {
    // Responses
    var getUserResponse by mutableStateOf<Response<User>>(Loading)
}