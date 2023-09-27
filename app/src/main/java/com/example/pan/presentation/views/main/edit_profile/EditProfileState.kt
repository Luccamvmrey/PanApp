package com.example.pan.presentation.views.main.edit_profile

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.user.User

class EditProfileState {
    // User
    var user by mutableStateOf<User?>(null)

    // Variables
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var imageUri by mutableStateOf<Uri?>(null)
    var bitmap by mutableStateOf<Bitmap?>(null)

    // Booleans
    var isUploadingImage by mutableStateOf(false)

    // Errors
    var nameError by mutableStateOf(InputError())
    var emailError by mutableStateOf(InputError())

    // Responses
    var getUserResponse by mutableStateOf<Response<User>>(Loading)
    var uploadProfilePictureResponse by mutableStateOf<Response<String>>(Loading)
    var updateUserResponse by mutableStateOf<Response<Boolean>>(Loading)
}