package com.project.fitx.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.project.fitx.data.Resource
import com.project.fitx.data.datasource.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow
    fun login(email: String, password: String) = viewModelScope.launch {
//        _loginFlow.value = Resource.Loading.data
        val result = repository.login(email = email, password = password)
        _loginFlow.value = result
    }

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if(repository.currentUser != null){
            _loginFlow.value = Resource.Success(repository.currentUser)
        }
    }

    private val _registerFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val registerFlow: StateFlow<Resource<FirebaseUser>?> = _registerFlow
    fun signup(email: String, password: String, name: String) = viewModelScope.launch {
//        _registerFlow.value = Resource.Loading.data
        val result = repository.signup(email = email, name = name, password = password)
        _registerFlow.value = result
    }

    fun delete() = viewModelScope.launch {
        repository.delete()
        _loginFlow.value = null
        _registerFlow.value = null
    }


    fun logout(){
        repository.logout()
        _loginFlow.value = null
        _registerFlow.value = null
    }
}