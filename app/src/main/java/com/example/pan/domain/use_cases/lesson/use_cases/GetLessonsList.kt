package com.example.pan.domain.use_cases.lesson.use_cases

import com.example.pan.domain.repository.lesson.LessonRepository

class GetLessonsList(
    private val repo: LessonRepository
) {
    suspend operator fun invoke(classId: String) = repo.getLessonsList(classId)
}