package com.example.pan.domain.use_cases.lesson.use_cases

import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.domain.repository.lesson.LessonRepository

class AddLesson (
    private val repo: LessonRepository
) {
    suspend operator fun invoke(
        lesson: Lesson,
        classId: String
    ) = repo.addLesson(lesson, classId)
}