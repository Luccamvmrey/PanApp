package com.example.pan.domain.models.lesson

data class Lesson(
    val videoUrl: String?,
    val lessonText: String?,
    val questions: List<Question>?
)
