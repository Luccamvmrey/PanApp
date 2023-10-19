package com.example.pan.domain.repository.user

import android.graphics.Bitmap
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.user.User

interface UserRepository {
    suspend fun createUser(
        name: String,
        email: String,
        password: String,
        isTeacher: Boolean
    ): Response<Boolean>

    suspend fun logUser(email: String, password: String): Response<Boolean>

    suspend fun getLoggedUser(): Response<User>

    suspend fun sendPasswordRecoveryEmail(email: String): Response<Boolean>

    suspend fun getUsers(): Response<List<User>>

    suspend fun uploadUserProfileImage(imageBitmap: Bitmap): Response<String>

    suspend fun updateUser(user: User): Response<Boolean>

    suspend fun signOut(): Response<Boolean>
}