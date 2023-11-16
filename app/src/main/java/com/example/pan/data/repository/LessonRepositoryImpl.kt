package com.example.pan.data.repository

import com.example.pan.domain.models.Response.*
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.domain.repository.lesson.LessonRepository
import com.example.pan.domain.repository.lesson.LessonResponse
import com.example.pan.domain.repository.lesson.LessonsList
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
    override suspend fun getLesson(lessonId: String): LessonResponse = try {
        val lesson = lessonsRef
            .document(lessonId)
            .get()
            .await()
            .toObject(Lesson::class.java)!!

        Success(lesson)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun getLessonsList(classId: String): LessonsList {
        try {
            val panClass = classesRef
                .document(classId)
                .get()
                .await()
                .toObject(PanClass::class.java)!!

            val lessonsIds = panClass.lessonIdList.ifEmpty {
                return Success(emptyList())
            }

            val lessons = lessonsRef
                .whereIn("lessonId", lessonsIds)
                .get()
                .await()
                .toObjects(Lesson::class.java)

            return Success(lessons)
        } catch (e: Exception) {
            return Failure(e)
        }
    }

    override suspend fun addLesson(lesson: Lesson, classId: String) = try {
        val classToAdd = classesRef
            .document(classId)
            .get()
            .await()
            .toObject(PanClass::class.java)!!

        val lessons = classToAdd.lessonIdList as ArrayList
        lessons.add(lesson.lessonId!!)

        lesson.order = lessons.size

        lessonsRef
            .document(lesson.lessonId)
            .set(lesson)
            .await()

        classesRef
            .document(classId)
            .update("lessonIdList", lessons)
            .await()

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }
}