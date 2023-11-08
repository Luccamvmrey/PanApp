package com.example.pan.domain.models.user

typealias LessonId = String
typealias ClassId = String

data class User(
    val userId: String? = null,
    val name: String? = null,
    val email: String? = null,
    val completedLessons: List<LessonId>? = emptyList(),
    val points: Int? = 0,
    val photoUrl: String? = null,
    val teacher: Boolean? = false,
    var panClassesId: List<ClassId>? = emptyList(),
)
