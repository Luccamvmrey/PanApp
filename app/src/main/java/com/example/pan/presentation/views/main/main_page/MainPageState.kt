package com.example.pan.presentation.views.main.main_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.domain.models.user.User

class MainPageState {
    // Data
    var lessonsList by mutableStateOf<List<Lesson>>(emptyList())
    var classesList by mutableStateOf<List<PanClass>>(emptyList())
    var user by mutableStateOf<User?>(null)

    // Variables
    var selectedClassId by mutableStateOf("")

    // Boolean
    var isReloading by mutableStateOf(false)

    // Responses
    var getLessonsListResponse by mutableStateOf<Response<List<Lesson>>>(Loading)
    var getClassesListResponse by mutableStateOf<Response<List<PanClass>>>(Loading)
    var getUserResponse by mutableStateOf<Response<User>>(Loading)

    // Profile
    var isProfileInvisibleChecked by mutableStateOf(false)
}