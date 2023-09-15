package com.example.pan.domain.repository.user

import android.content.Intent
import android.content.IntentSender
import com.example.pan.domain.models.user.SignInResult

interface GoogleUserRepository {
    suspend fun signIn(): IntentSender?

    suspend fun signInWithIntent(intent: Intent): SignInResult

    suspend fun signOut()
}