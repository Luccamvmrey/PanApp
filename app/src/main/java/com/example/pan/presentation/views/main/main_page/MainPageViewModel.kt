package com.example.pan.presentation.views.main.main_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pan.core.StringConstants.CLASS_ID_MUST_NOT_BE_EMPTY
import com.example.pan.core.StringConstants.CLASS_NAME_MUST_NOT_BE_EMPTY
import com.example.pan.core.createId
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.Response.Idle
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.user.User
import com.example.pan.domain.repository.classes.AddStudent
import com.example.pan.domain.repository.classes.Classes
import com.example.pan.domain.repository.classes.CreateClassResponse
import com.example.pan.domain.repository.lesson.LessonsList
import com.example.pan.domain.repository.user.SingleUser
import com.example.pan.domain.repository.user.UpdateUserResponse
import com.example.pan.domain.use_cases.classes.PanClassUseCases
import com.example.pan.domain.use_cases.lesson.LessonUseCases
import com.example.pan.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val panClassUseCases: PanClassUseCases,
    private var lessonUseCases: LessonUseCases
) : ViewModel() {
    var classNameError by mutableStateOf(InputError())
        private set
    var classIdError by mutableStateOf(InputError())
        private set

    var getUser by mutableStateOf<SingleUser>(Idle)
        private set
    var updateUser by mutableStateOf<UpdateUserResponse>(Idle)
        private set
    var addStudent by mutableStateOf<AddStudent>(Idle)
        private set
    var getClasses by mutableStateOf<Classes>(Idle)
        private set
    var createClass by mutableStateOf<CreateClassResponse>(Idle)
        private set
    var getLessons by mutableStateOf<LessonsList>(Idle)
        private set

    init {
        getUser()
    }

    // User
    fun getUser() = viewModelScope.launch {
        getUser = userUseCases.getLoggedUser()
    }

    fun getClassesListFromIds(user: User) = viewModelScope.launch {
        getClasses = panClassUseCases.getClassesListFromIds(
            user.panClassesId ?: emptyList()
        )
    }

    fun signOut() = viewModelScope.launch {
        userUseCases.signOut()
    }

    // Classes
    fun checkCreateClass(className: String) : Boolean {
        classNameError = InputError()
        if (className.isEmpty()) {
            classNameError = InputError(
                isError = true,
                message = CLASS_NAME_MUST_NOT_BE_EMPTY
            )
            return false
        }
        return true
    }

    fun createClass(className: String, teacher: User): PanClass {
        val classId = createId()

        return PanClass(
            className = className,
            teachers = listOf(
                teacher
            ),
            classId = classId
        )
    }

    fun addPanClassToFirebase(panClass: PanClass) = viewModelScope.launch {
        createClass = Loading
        createClass = panClassUseCases.createClass(panClass)
    }

    fun addClassIdToUser(classId: String, user: User) = viewModelScope.launch {
        val updatedUser = user.copy(
            panClassesId = user.panClassesId?.plus(classId)
        )

        updateUser = Loading
        updateUser = userUseCases.updateUser(updatedUser)
    }

    fun checkClassId(classId: String) : Boolean {
        classIdError = InputError()
        if (classId.isEmpty()) {
            classIdError = InputError(
                isError = true,
                message = CLASS_ID_MUST_NOT_BE_EMPTY
            )
            return false
        }
        return true
    }

    fun addStudentToClass(classId: String, user: User) = viewModelScope.launch {
        val studentId = user.userId

        addStudent = Loading
        addStudent = panClassUseCases.addStudentToClass(
            studentId = studentId!!,
            classId = classId
        )
    }

    // Lessons
    fun getLessons(classId: String) = viewModelScope.launch {
        getLessons = Loading
        getLessons = lessonUseCases.getLessonsList(classId)
    }
}