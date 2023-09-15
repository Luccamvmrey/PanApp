package com.example.pan.di

import com.example.pan.PanApp
import com.example.pan.core.Constants.USERS
import com.example.pan.core.Constants.WEB_CLIENT_ID
import com.example.pan.data.repository.GoogleUserRepositoryImpl
import com.example.pan.data.repository.UserRepositoryImpl
import com.example.pan.domain.repository.user.GoogleUserRepository
import com.example.pan.domain.repository.user.UserRepository
import com.example.pan.domain.use_cases.user.UserUseCases
import com.example.pan.domain.use_cases.user.use_cases.CreateUser
import com.example.pan.domain.use_cases.user.use_cases.GetLoggedUser
import com.example.pan.domain.use_cases.user.use_cases.GetUsers
import com.example.pan.domain.use_cases.user.use_cases.LogUser
import com.example.pan.domain.use_cases.user.use_cases.Logout
import com.example.pan.domain.use_cases.user.use_cases.SendPasswordRecoveryEmail
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
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

    // Google User
    @Provides
    @Named("oneTapClient")
    fun provideOneTapClient() = Identity.getSignInClient(PanApp.appContext)

    @Provides
    @Named("buildSignInRequest")
    fun provideBuildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(WEB_CLIENT_ID)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }


    // Repository providers
    @Provides
    fun provideUserRepository(
        @Named("auth")
        auth: FirebaseAuth,
        @Named("users")
        usersRef: CollectionReference,
    ) : UserRepository = UserRepositoryImpl(auth, usersRef)

    @Provides
    fun provideGoogleUserRepository(
        @Named("auth")
        auth: FirebaseAuth,
        @Named("users")
        usersRef: CollectionReference,
        @Named("oneTapClient")
        oneTapClient: SignInClient,
        @Named("buildSignInRequest")
        buildSignInRequest: BeginSignInRequest,
    ) : GoogleUserRepository = GoogleUserRepositoryImpl(auth, usersRef, oneTapClient, buildSignInRequest)

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
        logout = Logout(repo)
    )
}