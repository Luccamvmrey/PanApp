package com.example.pan.presentation.views.main.lesson_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Idle
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.Response.Success
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.domain.models.user.User
import com.example.pan.domain.repository.user.SingleUser
import com.example.pan.domain.repository.user.UpdateUserResponse
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
    var loggedUser by mutableStateOf(User())
    var currentLesson by mutableStateOf(Lesson())

    var getLesson by mutableStateOf<Response<Lesson>>(Idle)
        private set
    var getUser by mutableStateOf<SingleUser>(Idle)
        private set
    var updateUser by mutableStateOf<UpdateUserResponse>(Idle)
        private set

    init {
        savedStateHandle.get<String>("lessonId")?.let { lessonId ->
            getLesson(lessonId)
        }
        getUser()
    }

    private fun getLesson(lessonId: String) = viewModelScope.launch {
        getLesson = Loading
        getLesson = lessonUseCases.getLesson(lessonId)
    }

    private fun getUser() = viewModelScope.launch {
        getUser = userUseCases.getLoggedUser()
    }

    fun updateUser(successCount: Int) = viewModelScope.launch {
        if (loggedUser.completedLessons!!.contains(currentLesson.lessonId)) {
            updateUser = Success(true)

            return@launch
        }

        val updatedCompletedLessons = loggedUser.completedLessons!!.toMutableList()
        updatedCompletedLessons.add(currentLesson.lessonId!!)

        val newUser = loggedUser.copy(
            completedLessons = updatedCompletedLessons,
            points = loggedUser.points!! + successCount
        )

        updateUser = Loading
        updateUser = userUseCases.updateUser(newUser)
    }
}