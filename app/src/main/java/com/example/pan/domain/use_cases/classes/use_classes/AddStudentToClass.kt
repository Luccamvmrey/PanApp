package com.example.pan.domain.use_cases.classes.use_classes

import com.example.pan.domain.repository.classes.ClassesRepository

class AddStudentToClass (
    private val repo: ClassesRepository
) {
    suspend operator fun invoke(
        studentId: String,
        classId: String
    ) = repo.addStudentToClass(studentId, classId)
}