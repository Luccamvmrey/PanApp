package com.example.pan.domain.use_cases.classes.use_classes

import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.repository.classes.ClassesRepository

class CreateClass (
    private val repo: ClassesRepository
) {
    suspend operator fun invoke(
        panClass: PanClass
    ) = repo.createClass(panClass)
}