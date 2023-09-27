package com.example.pan.domain.use_cases.user.use_cases

import com.example.pan.domain.models.user.User
import com.example.pan.domain.repository.user.UserRepository

class UpdateUser constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(user: User) = repo.updateUser(user)
}