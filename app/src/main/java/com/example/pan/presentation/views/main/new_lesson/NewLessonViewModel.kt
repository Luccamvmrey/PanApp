package com.example.pan.presentation.views.main.new_lesson

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.domain.models.Response.Idle
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.domain.repository.classes.PanClassResponse
import com.example.pan.domain.repository.lesson.AddLessonResponse
import com.example.pan.domain.use_cases.classes.PanClassUseCases
import com.example.pan.domain.use_cases.lesson.LessonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewLessonViewModel @Inject constructor(
    private val panClassUseCases: PanClassUseCases,
    private val lessonUseCases: LessonUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var getClass by mutableStateOf<PanClassResponse>(Idle)
        private set
    var addLesson by mutableStateOf<AddLessonResponse>(Idle)
        private set

    init {
        savedStateHandle.get<String>("selectedClassId")?.let { classId ->
            getPanClass(classId)
        }
    }

    fun getPanClass(classId: String) = viewModelScope.launch {
        getClass = panClassUseCases.getPanClass(classId)
    }

    fun addLesson(lesson: Lesson, classId: String) = viewModelScope.launch {
        addLesson = lessonUseCases.addLesson(lesson, classId)
    }
}