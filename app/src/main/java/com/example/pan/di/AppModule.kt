package com.example.pan.di

import com.example.pan.core.Constants.CLASSES
import com.example.pan.core.Constants.LESSONS
import com.example.pan.core.Constants.USERS
import com.example.pan.data.repository.ClassesRepositoryImpl
import com.example.pan.data.repository.LessonRepositoryImpl
import com.example.pan.data.repository.UserRepositoryImpl
import com.example.pan.domain.repository.classes.ClassesRepository
import com.example.pan.domain.repository.lesson.LessonRepository
import com.example.pan.domain.repository.user.UserRepository
import com.example.pan.domain.use_cases.classes.PanClassUseCases
import com.example.pan.domain.use_cases.classes.use_classes.AddStudentToClass
import com.example.pan.domain.use_cases.classes.use_classes.AddTeacherToClass
import com.example.pan.domain.use_cases.classes.use_classes.CreateClass
import com.example.pan.domain.use_cases.classes.use_classes.GetClassesList
import com.example.pan.domain.use_cases.classes.use_classes.GetClassesListFromIds
import com.example.pan.domain.use_cases.classes.use_classes.GetPanClass
import com.example.pan.domain.use_cases.lesson.LessonUseCases
import com.example.pan.domain.use_cases.lesson.use_cases.AddLesson
import com.example.pan.domain.use_cases.lesson.use_cases.GetLesson
import com.example.pan.domain.use_cases.lesson.use_cases.GetLessonsList
import com.example.pan.domain.use_cases.user.UserUseCases
import com.example.pan.domain.use_cases.user.use_cases.CreateUser
import com.example.pan.domain.use_cases.user.use_cases.GetLoggedUser
import com.example.pan.domain.use_cases.user.use_cases.GetUsers
import com.example.pan.domain.use_cases.user.use_cases.LogUser
import com.example.pan.domain.use_cases.user.use_cases.SendPasswordRecoveryEmail
import com.example.pan.domain.use_cases.user.use_cases.SignOut
import com.example.pan.domain.use_cases.user.use_cases.UpdateUser
import com.example.pan.domain.use_cases.user.use_cases.UploadUserProfileImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Core
    @Provides
    @Named("auth")
    fun provideAuth() = Firebase.auth

    // User
    @Provides
    @Named("users")
    fun provideUsersRef() = Firebase.firestore.collection(USERS)

    @Provides
    @Named("storage")
    fun provideStorage() = Firebase.storage

    // Lessons
    @Provides
    @Named("lessons")
    fun provideLessonsRef() = Firebase.firestore.collection(LESSONS)

    // Classes
    @Provides
    @Named("classes")
    fun provideClassesRef() = Firebase.firestore.collection(CLASSES)

    // Repository providers
    @Provides
    fun provideUserRepository(
        @Named("auth")
        auth: FirebaseAuth,
        @Named("users")
        usersRef: CollectionReference,
        @Named("storage")
        storage: FirebaseStorage
    ) : UserRepository = UserRepositoryImpl(auth, usersRef, storage)

    @Provides
    fun provideLessonRepository(
        @Named("lessons")
        lessonsRef: CollectionReference,
        @Named("classes")
        classesRef: CollectionReference
    ) : LessonRepository = LessonRepositoryImpl(lessonsRef, classesRef)

    @Provides
    fun provideClassesRepository(
        @Named("classes")
        classesRef: CollectionReference,
        @Named("users")
        usersRef: CollectionReference
    ) : ClassesRepository = ClassesRepositoryImpl(classesRef, usersRef)

    // Use cases providers
    @Provides
    fun provideUserUseCases(
        repo: UserRepository
    ) = UserUseCases(
        logUser = LogUser(repo),
        createUser = CreateUser(repo),
        getLoggedUser = GetLoggedUser(repo),
        getUsers = GetUsers(repo),
        sendPasswordRecoveryEmail = SendPasswordRecoveryEmail(repo),
        uploadUserProfileImage = UploadUserProfileImage(repo),
        updateUser = UpdateUser(repo),
        signOut = SignOut(repo)
    )

    @Provides
    fun provideLessonUseCases(
        repo: LessonRepository
    ) = LessonUseCases(
        getLesson = GetLesson(repo),
        getLessonsList = GetLessonsList(repo),
        addLesson = AddLesson(repo)
    )

    @Provides
    fun providePanClassUseCases(
        repo: ClassesRepository
    ) = PanClassUseCases(
        createClass = CreateClass(repo),
        getPanClass = GetPanClass(repo),
        getClassesList = GetClassesList(repo),
        addStudentToClass = AddStudentToClass(repo),
        getClassesListFromIds = GetClassesListFromIds(repo),
        addTeacherToClass = AddTeacherToClass(repo)
    )
}