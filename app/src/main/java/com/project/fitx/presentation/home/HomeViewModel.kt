package com.project.fitx.presentation.home

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.project.fitx.data.datasource.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    val currentUser: FirebaseUser?
        get() = repository.currentUser


}