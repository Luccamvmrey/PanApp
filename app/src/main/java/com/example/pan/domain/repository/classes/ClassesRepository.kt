package com.example.pan.domain.repository.classes

import com.example.pan.domain.models.Response
import com.example.pan.domain.models.classes.PanClass

typealias PanClassResponse = Response<PanClass>
typealias Classes = Response<List<PanClass>>
typealias CreateClassResponse = Response<Boolean>
typealias AddStudent = Response<Boolean>
typealias AddTeacher = Response<Boolean>

interface ClassesRepository {
    suspend fun createClass(panClass: PanClass): CreateClassResponse

    suspend fun getPanClass(classId: String): PanClassResponse

    suspend fun getClassesList(): Classes

    suspend fun getClassesListFromIds(classIds: List<String>): Classes

    suspend fun addStudentToClass(studentId: String, classId: String): AddStudent

    suspend fun addTeacherToClass(teacherId: String, classId: String): AddTeacher
}