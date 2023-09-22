package com.example.pan.data.repository

import com.example.pan.core.StringConstants.NULL_USER
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.Failure
import com.example.pan.domain.models.Response.Success
import com.example.pan.domain.models.user.User
import com.example.pan.domain.repository.user.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    @Named("auth")
    private val auth: FirebaseAuth,
    @Named("users")
    private val usersRef: CollectionReference,
) : UserRepository {

    override suspend fun createUser(
        name: String,
        email: String,
        password: String
    ): Response<Boolean> = try {
        auth.createUserWithEmailAndPassword(
            email,
            password
        ).await()
        val userFromAuth = auth.currentUser
        val id = userFromAuth!!.uid

        val user = User(
            userId = id,
            name = name,
            email = email
        )
        usersRef.document(id).set(user).await()

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun logUser(email: String, password: String): Response<Boolean> = try {
        auth.signInWithEmailAndPassword(
            email,
            password
        ).await()

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun getLoggedUser(): Response<User> {
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

    override suspend fun sendPasswordRecoveryEmail(email: String): Response<Boolean> = try {
        auth.sendPasswordResetEmail(email).await()

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun getUsers(): Response<List<User>> = try {
        val querySnapshot = usersRef.get().await()
        val users = mutableListOf<User>()

        for (user in querySnapshot.documents) {
            users.add(user.toObject(User::class.java)!!)
        }

        Success(users)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun updateUser(user: User): Response<Boolean> = try {
        usersRef.document(user.userId!!).set(user).await()

        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override fun signOut() {
        auth.signOut()
    }
}