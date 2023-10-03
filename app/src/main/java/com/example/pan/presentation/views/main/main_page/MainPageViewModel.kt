package com.example.pan.presentation.views.main.main_page

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.domain.models.user.User
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
//    private var lessonUseCases: LessonUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(MainPageState())
    val state = _state.asStateFlow()

    init {
        getUser()
//        getLessonsList()
    }

//    fun getLessonsList() = viewModelScope.launch {
//        _state.value.getLessonsListResponse = lessonUseCases.getLessonsList()
//    }

    fun setLessonsList(lessonsList: List<Lesson>) {
        _state.value.lessonsList = lessonsList
    }

    fun getUser() = viewModelScope.launch {
        _state.value.getUserResponse = userUseCases.getLoggedUser()
    }

    fun setUser(user: User) {
        _state.value.user = user
    }

    fun signOut() = viewModelScope.launch {
        userUseCases.signOut()
    }

    fun setProfileInvisible(isProfileInvisible: Boolean) {
        _state.value.isProfileInvisibleChecked = isProfileInvisible
        Log.d("MainPageViewModel", "setProfileInvisible: $isProfileInvisible")
    }
}