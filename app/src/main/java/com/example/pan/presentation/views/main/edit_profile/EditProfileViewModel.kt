package com.example.pan.presentation.views.main.edit_profile

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.core.StringConstants.EMAIL_MUST_NOT_BE_EMPTY
import com.example.pan.core.StringConstants.NAME_MUST_NOT_BE_EMPTY
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.user.User
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

    fun setImageUri(imageUri: Uri) {
        _state.value.imageUri = imageUri
    }

    fun setBitmap(bitmap: Bitmap) {
        _state.value.bitmap = bitmap
    }

    fun setUser(user: User) {
        _state.value.user = user
    }

    fun checkFields(): Boolean {
        _state.value.nameError = InputError()
        _state.value.emailError = InputError()

        if (_state.value.name.isEmpty()) {
            _state.value.nameError = InputError(
                isError = true,
                message = NAME_MUST_NOT_BE_EMPTY
            )
        }

        if (_state.value.email.isEmpty()) {
            _state.value.emailError = InputError(
                isError = true,
                message = EMAIL_MUST_NOT_BE_EMPTY
            )
        }

        if (_state.value.nameError.isError || _state.value.emailError.isError) {
            return false
        }

        return true
    }

    fun getUser() = viewModelScope.launch {
        _state.value.getUserResponse = userUseCases.getLoggedUser()
    }

    fun uploadUserProfilePicture() = viewModelScope.launch {
        _state.value.isUploadingImage = true
        _state.value.uploadProfilePictureResponse = userUseCases.uploadUserProfileImage(
            _state.value.bitmap!!
        )
    }

    fun updateUser(user: User) = viewModelScope.launch {
        _state.value.updateUserResponse = userUseCases.updateUser(user)
    }
}