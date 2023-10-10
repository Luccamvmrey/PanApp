package com.example.pan.domain.use_cases.classes.use_classes

import com.example.pan.domain.repository.classes.ClassesRepository

class GetClassesList (
    private val repo: ClassesRepository
) {
    suspend operator fun invoke() = repo.getClassesList()
}