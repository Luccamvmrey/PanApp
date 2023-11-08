package com.example.pan.domain.models.classes

import com.example.pan.domain.models.user.User

typealias LessonId = String

data class PanClass(
    val classId: String? = null,
    val className: String? = null,
    val teachers: List<User> = emptyList(),
    val students: List<User> = emptyList(),
    val lessonIdList: List<LessonId> = emptyList(),
)
