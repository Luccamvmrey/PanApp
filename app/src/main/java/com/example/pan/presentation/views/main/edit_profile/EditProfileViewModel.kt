package com.example.pan.presentation.views.main.edit_profile

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.core.StringConstants.EMAIL_MUST_NOT_BE_EMPTY
import com.example.pan.core.StringConstants.NAME_MUST_NOT_BE_EMPTY
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.Response.Idle
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.user.User
import com.example.pan.domain.repository.user.SingleUser
import com.example.pan.domain.repository.user.UpdateUserResponse
import com.example.pan.domain.repository.user.UploadUserProfileImageResponse
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    var currentUser by mutableStateOf(User())
    var nameError by mutableStateOf(InputError())
    var emailError by mutableStateOf(InputError())
    var getUser by mutableStateOf<SingleUser>(Idle)
        private set
    var uploadImage by mutableStateOf<UploadUserProfileImageResponse>(Idle)
        private set
    var updateUser by mutableStateOf<UpdateUserResponse>(Idle)
        private set

    init {
        getUser()
    }

    fun checkFields(name: String, email: String): Boolean {
        nameError = InputError()
        emailError = InputError()

        if (name.isEmpty()) {
            nameError = InputError(
                isError = true,
                message = NAME_MUST_NOT_BE_EMPTY
            )
        }

        if (email.isEmpty()) {
            emailError = InputError(
                isError = true,
                message = EMAIL_MUST_NOT_BE_EMPTY
            )
        }

        if (nameError.isError || emailError.isError) {
            return false
        }

        return true
    }

    private fun getUser() = viewModelScope.launch {
        getUser = Loading
        getUser = userUseCases.getLoggedUser()
    }

    fun uploadUserProfilePicture(bitmap: Bitmap) = viewModelScope.launch {
        uploadImage = Loading
        uploadImage = userUseCases.uploadUserProfileImage(bitmap)
    }

    fun updateUser(
        user: User? = null,
        name: String? = null,
        email: String? = null
    ) = viewModelScope.launch {
        val newUser = user
            ?: currentUser.copy(
                name = name!!,
                email = email!!
            )
        updateUser = Loading
        updateUser = userUseCases.updateUser(newUser)
    }
}