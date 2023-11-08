package com.example.pan.domain.models.lesson

data class Question(
    val question: String? = null,
    val answers: List<String>? = null,
    val correctAnswerIndex: Int? = null
)
