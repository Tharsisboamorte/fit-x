package com.project.fitx.data

import com.google.firebase.auth.FirebaseUser

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    data object Loading: Resource<Nothing>()
}