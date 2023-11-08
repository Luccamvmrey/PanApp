package com.example.pan.domain.models.lesson

typealias LessonId = String

data class Lesson(
    val lessonId: String? = null,
    val lessonTitle: String? = null,
    val preRequisite: List<LessonId>? = emptyList(),
    val videoUrl: String? = null,
    val lessonText: String? = null,
    val questions: List<Question>? = null
)
