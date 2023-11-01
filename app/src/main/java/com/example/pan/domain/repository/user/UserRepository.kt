package com.example.pan.domain.repository.user

import android.graphics.Bitmap
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.user.User

typealias SingleUser = Response<User>
typealias Users = Response<List<User>>
typealias LogUserResponse = Response<Boolean>
typealias CreateUserResponse = Response<Boolean>
typealias UpdateUserResponse = Response<Boolean>
typealias SendPasswordRecoveryEmailResponse = Response<Boolean>
typealias UploadUserProfileImageResponse = Response<String>
typealias SignOutResponse = Response<Boolean>

interface UserRepository {
    suspend fun createUser(
        name: String,
        email: String,
        password: String,
        isTeacher: Boolean
    ): CreateUserResponse

    suspend fun logUser(email: String, password: String): LogUserResponse

    suspend fun getLoggedUser(): SingleUser

    suspend fun sendPasswordRecoveryEmail(email: String): SendPasswordRecoveryEmailResponse

    suspend fun getUsers(): Users

    suspend fun uploadUserProfileImage(imageBitmap: Bitmap): UploadUserProfileImageResponse

    suspend fun updateUser(user: User): UpdateUserResponse

    suspend fun signOut(): SignOutResponse
}