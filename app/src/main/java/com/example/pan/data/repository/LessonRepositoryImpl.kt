package com.example.pan.data.repository

import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.*
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.domain.repository.lesson.LessonRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class LessonRepositoryImpl @Inject constructor(
    @Named("lessons")
    private val lessonsRef: CollectionReference,
    @Named("classes")
    private val classesRef: CollectionReference
) : LessonRepository {
    override suspend fun getLesson(lessonId: String): Response<Lesson> = try {
        val lesson = lessonsRef
            .document(lessonId)
            .get()
            .await()
            .toObject(Lesson::class.java)!!

        Success(lesson)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun getLessonsList(): Response<List<Lesson>> = try {
        val lessons = lessonsRef
            .get()
            .await()
            .toObjects(Lesson::class.java)

        Success(lessons)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun addLesson(lesson: Lesson, classId: String) {
        val classToAdd = classesRef
            .document(classId)
            .get()
            .await()
            .toObject(PanClass::class.java)!!

        val lessons = classToAdd.lessonsList as ArrayList
        lessons.add(lesson)

        classesRef
            .document(classId)
            .update("lessonsList", lessons)
            .await()
    }
}