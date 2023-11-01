package com.example.pan.data.repository

import android.graphics.Bitmap
import com.example.pan.core.StringConstants.NULL_USER
import com.example.pan.domain.models.Response.Failure
import com.example.pan.domain.models.Response.Success
import com.example.pan.domain.models.user.User
import com.example.pan.domain.repository.user.CreateUserResponse
import com.example.pan.domain.repository.user.LogUserResponse
import com.example.pan.domain.repository.user.SendPasswordRecoveryEmailResponse
import com.example.pan.domain.repository.user.SignOutResponse
import com.example.pan.domain.repository.user.SingleUser
import com.example.pan.domain.repository.user.UpdateUserResponse
import com.example.pan.domain.repository.user.UploadUserProfileImageResponse
import com.example.pan.domain.repository.user.UserRepository
import com.example.pan.domain.repository.user.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    @Named("auth")
    private val auth: FirebaseAuth,
    @Named("users")
    private val usersRef: CollectionReference,
    @Named("storage")
    private val storage: FirebaseStorage
) : UserRepository {

    override suspend fun createUser(
        name: String,
        email: String,
        password: String,
        isTeacher: Boolean
    ): CreateUserResponse = try {
        auth.createUserWithEmailAndPassword(
            email,
            password
        ).await()
        val userFromAuth = auth.currentUser
        val id = userFromAuth!!.uid

        val user = User(
            userId = id,
            name = name,
            email = email,
            teacher = isTeacher
        )
        usersRef.document(id).set(user).await()

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun logUser(email: String, password: String): LogUserResponse = try {
        auth.signInWithEmailAndPassword(
            email,
            password
        ).await()

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun getLoggedUser(): SingleUser {
        val userFromAuth = auth.currentUser ?: return Failure(Exception(NULL_USER))

        return try {
            val querySnapshot = usersRef
                .whereEqualTo("userId", userFromAuth.uid)
                .get()
                .await()

            if (querySnapshot.isEmpty) {
                Failure(Exception(NULL_USER))
            } else {
                val user = querySnapshot.documents[0].toObject(User::class.java)!!

                Success(user)
            }

        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun sendPasswordRecoveryEmail(email: String)
    : SendPasswordRecoveryEmailResponse = try {
        auth.sendPasswordResetEmail(email).await()

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun getUsers(): Users = try {
        val querySnapshot = usersRef.get().await()
        val users = mutableListOf<User>()

        for (user in querySnapshot.documents) {
            users.add(user.toObject(User::class.java)!!)
        }

        Success(users)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun uploadUserProfileImage(imageBitmap: Bitmap)
    : UploadUserProfileImageResponse = try {
        val storageRef = storage.reference
        val timestamp = System.currentTimeMillis()
        val obsImageRef = storageRef.child("profile_images/$timestamp")
        val baos = ByteArrayOutputStream()

        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)

        val fileInBytes = baos.toByteArray()
        obsImageRef.putBytes(fileInBytes).await()

        val downloadUri = obsImageRef.downloadUrl.await()

        Success(downloadUri.toString())
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun updateUser(user: User): UpdateUserResponse = try {
        usersRef.document(user.userId!!).set(user).await()

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun signOut(): SignOutResponse = try {
        auth.signOut()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }
}