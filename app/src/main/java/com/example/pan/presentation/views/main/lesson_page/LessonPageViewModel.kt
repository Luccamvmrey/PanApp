package com.example.pan.presentation.views.main.lesson_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Idle
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.domain.use_cases.lesson.LessonUseCases
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonPageViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val lessonUseCases: LessonUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var getLesson by mutableStateOf<Response<Lesson>>(Idle)
        private set

    init {
        savedStateHandle.get<String>("lessonId")?.let { lessonId ->
            getLesson(lessonId)
        }
    }

    private fun getLesson(lessonId: String) = viewModelScope.launch {
        getLesson = lessonUseCases.getLesson(lessonId)
    }
}