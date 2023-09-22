package com.example.pan.di

import com.example.pan.core.Constants.LESSONS
import com.example.pan.core.Constants.USERS
import com.example.pan.data.repository.LessonRepositoryImpl
import com.example.pan.data.repository.UserRepositoryImpl
import com.example.pan.domain.repository.lesson.LessonRepository
import com.example.pan.domain.repository.user.UserRepository
import com.example.pan.domain.use_cases.lesson.LessonUseCases
import com.example.pan.domain.use_cases.lesson.use_cases.GetLesson
import com.example.pan.domain.use_cases.lesson.use_cases.GetLessonsList
import com.example.pan.domain.use_cases.user.UserUseCases
import com.example.pan.domain.use_cases.user.use_cases.CreateUser
import com.example.pan.domain.use_cases.user.use_cases.GetLoggedUser
import com.example.pan.domain.use_cases.user.use_cases.GetUsers
import com.example.pan.domain.use_cases.user.use_cases.LogUser
import com.example.pan.domain.use_cases.user.use_cases.SendPasswordRecoveryEmail
import com.example.pan.domain.use_cases.user.use_cases.SignOut
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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

    // Lessons
    @Provides
    @Named("lessons")
    fun provideLessonsRef() = Firebase.firestore.collection(LESSONS)

    // Repository providers
    @Provides
    fun provideUserRepository(
        @Named("auth")
        auth: FirebaseAuth,
        @Named("users")
        usersRef: CollectionReference,
    ) : UserRepository = UserRepositoryImpl(auth, usersRef)

    @Provides
    fun provideLessonRepository(
        @Named("lessons")
        lessonsRef: CollectionReference
    ) : LessonRepository = LessonRepositoryImpl(lessonsRef)

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
        signOut = SignOut(repo)
    )

    @Provides
    fun provideLessonUseCases(
        repo: LessonRepository
    ) = LessonUseCases(
        getLesson = GetLesson(repo),
        getLessonsList = GetLessonsList(repo)
    )
}