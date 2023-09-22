package com.example.pan.domain.models.lesson

data class Lesson(
    val lessonId: String?,
    val lessonTitle: String?,
    val videoUrl: String?,
    val lessonText: String?,
    val questions: List<Question>?
)
