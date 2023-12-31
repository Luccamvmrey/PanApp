package com.example.pan.data.repository

import com.example.pan.domain.models.Response.Failure
import com.example.pan.domain.models.Response.Success
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.user.User
import com.example.pan.domain.repository.classes.AddStudent
import com.example.pan.domain.repository.classes.Classes
import com.example.pan.domain.repository.classes.ClassesRepository
import com.example.pan.domain.repository.classes.PanClassResponse
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ClassesRepositoryImpl @Inject constructor(
    @Named("classes")
    private val  classesRef: CollectionReference,
    @Named("users")
    private val usersRef: CollectionReference
) : ClassesRepository {
    override suspend fun createClass(panClass: PanClass) = try {
        classesRef
            .document(panClass.classId!!)
            .set(panClass)
            .await()

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun getPanClass(classId: String): PanClassResponse = try {
        val panClass = classesRef
            .document(classId)
            .get()
            .await()
            .toObject(PanClass::class.java)!!

        Success(panClass)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun getClassesList(): Classes = try {
        val classes = classesRef
            .get()
            .await()
            .toObjects(PanClass::class.java)

        Success(classes)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun getClassesListFromIds(classIds: List<String>): Classes {
        try {
            if (classIds.isEmpty()) {
                return Success(emptyList())
            }

            val classes = classesRef
                .whereIn("classId", classIds)
                .get()
                .await()
                .toObjects(PanClass::class.java)

            return Success(classes)
        } catch (e: Exception) {
            return Failure(e)
        }
    }

    override suspend fun addStudentToClass(studentId: String, classId: String) : AddStudent {
        try {
            val panClassRef = classesRef
                .document(classId)
                .get()
                .await()

            if (panClassRef.data.isNullOrEmpty()) {
                return Success(false)
            }

            val student = usersRef
                .document(studentId)
                .get()
                .await()
                .toObject(User::class.java)!!

            panClassRef.toObject(PanClass::class.java)?.let {
                val students = it.students as ArrayList
                students.add(student)

                classesRef
                    .document(classId)
                    .update("students", students)
                    .await()
            }

            return Success(true)
        } catch (e: Exception) {
            return Failure(e)
        }
    }

    override suspend fun addTeacherToClass(teacherId: String, classId: String) = try {
        val panClassRef = classesRef
            .document(classId)
            .get()
            .await()

        val teacher = usersRef
            .document(teacherId)
            .get()
            .await()
            .toObject(User::class.java)!!

        panClassRef.toObject(PanClass::class.java)?.let {
            val teachers = it.teachers as ArrayList
            teachers.add(teacher)

            classesRef
                .document(classId)
                .update("teachers", teachers)
                .await()
        }

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }
}