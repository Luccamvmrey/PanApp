package com.example.pan.data.repository

import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Failure
import com.example.pan.domain.models.Response.Success
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.user.User
import com.example.pan.domain.repository.classes.ClassesRepository
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
        val newClassRef = classesRef
            .add(panClass)
            .await()

        val classId = newClassRef.id

        classesRef
            .document(classId)
            .update("classId", classId)
            .await()

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun getClassesList(): Response<List<PanClass>> = try {
        val classes = classesRef
            .get()
            .await()
            .toObjects(PanClass::class.java)

        Success(classes)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun getClassesListFromIds(classIds: List<String>): Response<List<PanClass>> = try {
        val classes = classesRef
            .whereIn("classId", classIds)
            .get()
            .await()
            .toObjects(PanClass::class.java)

        Success(classes)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun addStudentToClass(studentId: String, classId: String) = try {
        val panClassRef = classesRef
            .document(classId)
            .get()
            .await()

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
                .update("studentsList", students)
                .await()
        }

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }
}