package com.project.fitx.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.project.fitx.data.datasource.auth.AuthRepository
import com.project.fitx.data.datasource.training.TrainingRepository
import com.project.fitx.data.model.Training
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val auth: AuthRepository,
    private val trainingRepository: TrainingRepository
) : ViewModel() {
    val currentUser: FirebaseUser?
        get() = auth.currentUser

    fun createTraining(training: Training) = viewModelScope.launch{
        trainingRepository.createTraining(training = training)
    }
}