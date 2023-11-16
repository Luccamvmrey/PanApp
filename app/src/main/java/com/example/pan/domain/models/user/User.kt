package com.example.pan.domain.models.user

import com.example.pan.domain.models.lesson.Lesson

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

fun User.getAllowedLessons(completeList: List<Lesson>): List<Lesson> {
    val allowedLessons = mutableListOf<Lesson>()

    completeList.forEach { lesson ->
        if (this.completedLessons!!.contains(lesson.prerequisite)) {
            allowedLessons.add(lesson)
        }
        if (lesson.prerequisite.isNullOrEmpty()) {
            allowedLessons.add(lesson)
        }
    }

    return allowedLessons
}