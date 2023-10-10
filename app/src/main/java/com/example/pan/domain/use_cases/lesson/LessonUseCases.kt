package com.example.pan.domain.use_cases.lesson

import com.example.pan.domain.use_cases.lesson.use_cases.AddLesson
import com.example.pan.domain.use_cases.lesson.use_cases.GetLesson
import com.example.pan.domain.use_cases.lesson.use_cases.GetLessonsList

data class LessonUseCases(
    val getLesson: GetLesson,
    val getLessonsList: GetLessonsList,
    val addLesson: AddLesson,
)
