package com.project.fitx.data.datasource.training

import com.project.fitx.data.Resource
import com.project.fitx.domain.entities.TrainingEntity

interface TrainingRepository {

    suspend fun createTraining(description: String)

    suspend fun getTraining(): Resource<List<TrainingEntity>>

    suspend fun deleteTraining(trainingId: String)

    suspend fun editTraining(trainingId: String, updatedTraining: String)

}