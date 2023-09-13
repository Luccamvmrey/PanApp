package com.example.pan.domain.repository.user

import com.example.pan.domain.models.Response
import com.example.pan.domain.models.user.User

interface UserRepository {
    suspend fun createUser(name: String, email: String, password: String): Response<Boolean>

    suspend fun logUser(email: String, password: String): Response<Boolean>

    suspend fun getLoggedUser(): Response<User>

    suspend fun sendPasswordRecoveryEmail(email: String): Response<Boolean>

    suspend fun getUsers(): Response<List<User>>

    fun logout()
}