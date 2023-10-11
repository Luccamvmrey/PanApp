package com.example.pan.domain.use_cases.classes.use_classes

import com.example.pan.domain.repository.classes.ClassesRepository

class GetClassesListFromIds(
    private val repo: ClassesRepository
) {
    suspend operator fun invoke(classIds: List<String>) = repo.getClassesListFromIds(classIds)
}