package com.example.pan.domain.repository.classes

import com.example.pan.domain.models.Response
import com.example.pan.domain.models.classes.PanClass

interface ClassesRepository {
    suspend fun createClass(panClass: PanClass): Response<Boolean>

    suspend fun getClassesList(): Response<List<PanClass>>

    suspend fun getClassesListFromIds(classIds: List<String>): Response<List<PanClass>>

    suspend fun addStudentToClass(studentId: String, classId: String): Response<Boolean>

    suspend fun addTeacherToClass(teacherId: String, classId: String): Response<Boolean>
}