package com.example.pan.domain.models.user

data class User(
    val userId: String? = null,
    val name: String? = null,
    val email: String? = null,
    val completedLessons: List<Map<String, String>>? = emptyList(),
    val points: Int? = 0,
    val photoUrl: String? = null,
    val isTeacher: Boolean? = false,
    val panClassesId: List<String>? = emptyList(),
)
