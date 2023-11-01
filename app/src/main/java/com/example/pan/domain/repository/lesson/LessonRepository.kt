package com.example.pan.domain.repository.lesson

import com.example.pan.domain.models.Response
import com.example.pan.domain.models.lesson.Lesson

typealias LessonsList = Response<List<Lesson>>
typealias LessonResponse = Response<Lesson>

interface LessonRepository {
    suspend fun getLesson(lessonId: String): LessonResponse

    suspend fun getLessonsList(classId: String): LessonsList

    suspend fun addLesson(lesson: Lesson, classId: String)
}