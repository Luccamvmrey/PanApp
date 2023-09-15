package com.example.pan.data.repository

import android.content.Intent
import android.content.IntentSender
import com.example.pan.domain.models.user.SignInResult
import com.example.pan.domain.models.user.User
import com.example.pan.domain.repository.user.GoogleUserRepository
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class GoogleUserRepositoryImpl @Inject constructor(
    @Named("auth")
    private val auth: FirebaseAuth,
    @Named("users")
    private val usersRef: CollectionReference,
    @Named("oneTapClient")
    private val oneTapClient: SignInClient,
    @Named("buildSignInRequest")
    private val buildSignInRequest: BeginSignInRequest,
) : GoogleUserRepository {
    override suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    override suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val idToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(idToken, null)

        return try {
            val userFromGoogle = auth.signInWithCredential(googleCredentials).await().user
            val user = userFromGoogle?.run {
                User (
                    userId = uid,
                    name = displayName ?: "",
                    email = email ?: "",
                    photoUrl = photoUrl?.toString(),
                )
            }

            val querySnapshot = usersRef.get().await()
            for (document in querySnapshot) {
                if (document["userId"] == user?.userId) {
                    return SignInResult(
                        data = user,
                        errorMessage = null
                    )
                }
            }
            user?.run {
                user.userId?.let { usersRef.document(it).set(user).await() }
            }

            SignInResult(
                data = user,
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    override suspend fun signOut() {
        try {
            oneTapClient.signOut()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }
}