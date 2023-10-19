package com.example.pan.presentation.views.main.main_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.core.createClassId
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.domain.models.user.User
import com.example.pan.domain.use_cases.classes.PanClassUseCases
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val panClassUseCases: PanClassUseCases,
//    private var lessonUseCases: LessonUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(MainPageState())
    val state = _state.asStateFlow()

    init {
        getUser()
//        getLessonsList()
    }

    // Lessons

//    fun getLessonsList() = viewModelScope.launch {
//        _state.value.getLessonsListResponse = lessonUseCases.getLessonsList()
//    }

    fun setLessonsList(lessonsList: List<Lesson>) {
        _state.value.lessonsList = lessonsList
    }
     // User

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
    }

    // PanClasses

    fun createClass(className: String): PanClass {
        val classId = createClassId()

        return PanClass(
            className = className,
            teachers = listOf(
                _state.value.user!!
            ),
            classId = classId
        )
    }

    fun addPanClassToFirebase(panClass: PanClass) = viewModelScope.launch {
        _state.value.createClassResponse = panClassUseCases.createClass(panClass)
    }

    fun addClassIdToUser(classId: String) = viewModelScope.launch {
        val user = _state.value.user!!
        val panClassesId = user.panClassesId as ArrayList
        panClassesId.add(classId)

        user.panClassesId = panClassesId

        _state.value.updateUserResponse = userUseCases.updateUser(user)
    }

    fun addStudentToClass(classId: String) = viewModelScope.launch {
        val studentId = _state.value.user?.userId

        _state.value.addStudentResponse = panClassUseCases.addStudentToClass(
            studentId = studentId!!,
            classId = classId
        )
    }

    fun getClassesListFromIds() = viewModelScope.launch {
        _state.value.getClassesListResponse = panClassUseCases.getClassesListFromIds(
            _state.value.user?.panClassesId ?: emptyList()
        )
    }

    fun setClassesList(panClasses: List<PanClass>) {
        _state.value.classesList = panClasses
    }

    fun setSelectedClassId(classId: String) {
        _state.value.selectedClassId = classId
    }
}