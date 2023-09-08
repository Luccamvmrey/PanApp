package com.example.pan.domain.use_cases.user.use_cases

import com.example.pan.domain.repository.user.UserRepository

class SendPasswordRecoveryEmail(
    private val repo: UserRepository
) {
    suspend operator fun invoke(
        email: String
    ) = repo.sendPasswordRecoveryEmail(email)
}