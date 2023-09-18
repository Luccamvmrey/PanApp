package com.example.pan.domain.repository.lesson

import com.example.pan.domain.models.Response
import com.example.pan.domain.models.lesson.Lesson

interface LessonRepository {
    suspend fun getLesson(lessonId: String): Response<Lesson>

    suspend fun getLessonsList(): Response<List<Lesson>>
}