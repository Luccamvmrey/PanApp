package com.example.pan.domain.models.classes

import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.domain.models.user.User

data class PanClass(
    val classId: String? = null,
    val className: String? = null,
    val teachers: List<User> = emptyList(),
    val students: List<User> = emptyList(),
    val lessonsList: List<Lesson> = emptyList(),
)
