package com.example.pan.presentation.views.main.new_lesson

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.core.StringConstants.INVALID_VIDEO_URL
import com.example.pan.core.StringConstants.LESSON_TEXT_MUST_NOT_BE_EMPTY
import com.example.pan.core.StringConstants.LESSON_TITLE_MUST_NOT_BE_EMPTY
import com.example.pan.core.createId
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.Response.Idle
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.domain.models.lesson.isValidUrl
import com.example.pan.domain.repository.classes.PanClassResponse
import com.example.pan.domain.repository.lesson.AddLessonResponse
import com.example.pan.domain.repository.lesson.LessonsList
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
    var panClass by mutableStateOf(PanClass())
    var lessonsList by mutableStateOf<List<Lesson>>(emptyList())

    var lessonTitleError by mutableStateOf(InputError())
    var videoUrlError by mutableStateOf(InputError())
    var lessonTextError by mutableStateOf(InputError())

    var getClass by mutableStateOf<PanClassResponse>(Idle)
        private set
    var addLesson by mutableStateOf<AddLessonResponse>(Idle)
        private set
    var getLessonsList by mutableStateOf<LessonsList>(Idle)
        private set

    init {
        savedStateHandle.get<String>("selectedClassId")?.let { classId ->
            getPanClass(classId)
        }
    }

    private fun getPanClass(classId: String) = viewModelScope.launch {
        getClass = Loading
        getClass = panClassUseCases.getPanClass(classId)
    }

    fun getLessonsList() = viewModelScope.launch {
        getLessonsList = lessonUseCases.getLessonsList(panClass.classId!!)
    }

    fun addLesson(lesson: Lesson) = viewModelScope.launch {
        val newLesson = lesson.copy(
            lessonId = createId()
        )

        addLesson = Loading
        addLesson = lessonUseCases.addLesson(
            newLesson,
            panClass.classId!!
        )
    }

    fun checkFirstForm(lessonTitle: String, lessonText: String, videoUrl: String): Boolean {
        lessonTitleError = InputError()
        lessonTextError = InputError()
        videoUrlError = InputError()

        if (lessonTitle.isEmpty()) {
            lessonTitleError = InputError(
                isError = true,
                message = LESSON_TITLE_MUST_NOT_BE_EMPTY
            )
        }

        if (lessonText.isEmpty()) {
            lessonTextError = InputError(
                isError = true,
                message = LESSON_TEXT_MUST_NOT_BE_EMPTY
            )
        }

        if (videoUrl.isValidUrl()) {
            videoUrlError = InputError(
                isError = true,
                message = INVALID_VIDEO_URL
            )
        }

        if (lessonTitleError.isError || lessonTextError.isError || videoUrlError.isError) {
            return false
        }

        return true
    }
}