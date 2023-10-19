package com.example.pan.domain.use_cases.user.use_cases

import com.example.pan.domain.repository.user.UserRepository

class CreateUser(
    private val repo: UserRepository
) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        isTeacher: Boolean
    ) = repo.createUser(name, email, password, isTeacher)
}