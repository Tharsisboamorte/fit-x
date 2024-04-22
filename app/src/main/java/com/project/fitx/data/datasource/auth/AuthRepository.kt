package com.project.fitx.data.datasource.auth

import com.google.firebase.auth.FirebaseUser
import com.project.fitx.data.Resource

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    suspend fun delete()
    fun logout()
}