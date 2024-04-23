package com.project.fitx.data.datasource.training

import com.project.fitx.data.Resource
import com.project.fitx.data.model.Training

interface TrainingRepository {

    suspend fun createTraining(description: String)

    suspend fun getTraining(): Resource<List<Training>>

    suspend fun deleteTraining(trainingId: String)

    suspend fun editTraining(trainingId: String, updatedTraining: Training)

}