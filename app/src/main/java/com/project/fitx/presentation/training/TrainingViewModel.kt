package com.project.fitx.presentation.training

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.fitx.data.Resource
import com.project.fitx.data.datasource.training.ExerciseRepository
import com.project.fitx.data.datasource.training.TrainingRepository
import com.project.fitx.data.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val repository: ExerciseRepository,
    private val trainingRepository: TrainingRepository,
) : ViewModel() {

    private val _listFlow = mutableListOf<Exercise>()
    val exercisesList: MutableList<Exercise> = _listFlow

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    init {
        getExercises()
    }

    private fun getExercises() = viewModelScope.launch {
        _loading.value = true // Set loading state to true before making the request
        when (val result = repository.getExercises()) {
            is Resource.Success -> {
                result.data?.let { exercises ->
                    Log.d("Exercices_viewmodels", "getExercises: $exercises")
                    _listFlow.addAll(exercises)
                }
                _loading.value = false // Set loading state to false after successful request
            }

            is Resource.Error -> {
                // Handle error
                result.message
                _loading.value = false // Set loading state to false after error
            }
        }
    }


    fun trainingEdit(trainingId: String, newTitle: String) = viewModelScope.launch {
        try {
            trainingRepository.editTraining(trainingId = trainingId, updatedTraining = newTitle)
        } catch (e: Exception) {
            //SHOW SNACK BAR
        }
    }

    fun trainingDelete(trainingId: String) = viewModelScope.launch {
        try {
            trainingRepository.deleteTraining(trainingId = trainingId)
        } catch (e: Exception) {
            //SHOW SNACK BAR
        }
    }
}