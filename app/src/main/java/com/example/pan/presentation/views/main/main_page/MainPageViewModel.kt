package com.example.pan.presentation.views.main.main_page

import androidx.lifecycle.ViewModel
import com.example.pan.domain.use_cases.lesson.LessonUseCases
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private var lessonUseCases: LessonUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(MainPageState())
    val state = _state.asStateFlow()

}