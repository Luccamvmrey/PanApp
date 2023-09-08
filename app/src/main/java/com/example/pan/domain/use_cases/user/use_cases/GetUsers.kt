package com.example.pan.domain.use_cases.user.use_cases

import com.example.pan.domain.repository.user.UserRepository

class GetUsers(
    private val repo: UserRepository
) {
    suspend operator fun invoke() = repo.getUsers()
}