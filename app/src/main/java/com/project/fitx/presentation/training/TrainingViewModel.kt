package com.project.fitx.presentation.training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.fitx.data.Resource
import com.project.fitx.data.datasource.training.ExerciseRepository
import com.project.fitx.data.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val repository: ExerciseRepository
) : ViewModel() {

    private val _listFlow = mutableListOf<List<Exercise>>()
    val exercisesList: List<Exercise>
        get() = _listFlow
            .filterIsInstance<Resource.Success<List<Exercise>>>()
            .flatMap { it.data ?: emptyList() }

    fun getExercises() = viewModelScope.launch {
        val response = repository.getExercises()
        response.data?.let {
            _listFlow.add(it)
        }
    }
}