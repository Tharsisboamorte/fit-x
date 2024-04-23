package com.project.fitx.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.project.fitx.data.Resource
import com.project.fitx.data.datasource.auth.AuthRepository
import com.project.fitx.data.datasource.training.TrainingRepository
import com.project.fitx.domain.entities.TrainingEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val auth: AuthRepository,
    private val repository: TrainingRepository
) : ViewModel() {
    val currentUser: FirebaseUser?
        get() = auth.currentUser

    private val _listFlow = mutableListOf<TrainingEntity>()
    val trainingList: MutableList<TrainingEntity> = _listFlow

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    init {
        getListOfTrainings()
    }

    fun reset(){
        _listFlow.clear()
        _loading.value = true
        getListOfTrainings()
    }

    fun createTraining(description: String) = viewModelScope.launch{
        repository.createTraining(description = description)
    }

    private fun getListOfTrainings() = viewModelScope.launch{
        _loading.value = true
        when (val result = repository.getTraining()) {
            is Resource.Success -> {
                result.data?.let { trainings ->
                    Log.d("Home_viewmodels", "getTrainings: $trainings")
                    _listFlow.addAll(trainings)
                }
                _loading.value = false
            }

            is Resource.Error -> {
                result.message
                _loading.value = false
            }
        }
    }
}