package com.example.pan.domain.models.lesson

import android.util.Patterns

typealias LessonId = String

data class Lesson(
    val lessonId: String? = null,
    val lessonTitle: String? = null,
    val prerequisite: LessonId? = null,
    val videoUrl: String? = null,
    val lessonText: String? = null,
    var questions: List<Question>? = null
)

fun String.isValidUrl(): Boolean {
    return Patterns.WEB_URL.matcher(this).matches()
}