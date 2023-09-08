package com.example.pan.domain.models.user

data class User(
    val userId: String? = null,
    val name: String? = null,
    val email: String? = null,
    val watchedLessons: Int? = null,
    val points: Int? = null,
    val photoUrl: String? = null,
)
