package com.project.fitx.data.datasource.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.project.fitx.data.Resource
import com.project.fitx.utils.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val response = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(response.user)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message!!)
        }
    }

    override suspend fun signup(
        name: String,
        email: String,
        password: String
    ): Resource<FirebaseUser> {
        return try {
            val response = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            response.user?.updateProfile(
                UserProfileChangeRequest.Builder().setDisplayName(name).build()
            )?.await()
            Resource.Success(response.user)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message!!)
        }
    }

    override suspend fun delete() {
        try {
            val response = firebaseAuth.currentUser?.delete()?.await()
            Resource.Success(response)
        } catch (e: FirebaseAuthException) {
            Log.e("DeleteAccount", "FirebaseAuthException: ${e.message}", e)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("Error: ${e.message}", null)
            Log.e("DeleteAccount", "Exception: ${e.message}", e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}