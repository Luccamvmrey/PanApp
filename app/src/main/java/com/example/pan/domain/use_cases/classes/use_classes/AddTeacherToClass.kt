package com.example.pan.domain.use_cases.classes.use_classes

import com.example.pan.domain.repository.classes.ClassesRepository

class AddTeacherToClass constructor(
    private val repo: ClassesRepository
) {
    suspend operator fun invoke(
        teacherId: String,
        classId: String
    ) = repo.addTeacherToClass(teacherId, classId)
}