package com.project.fitx.data.datasource.training

interface TrainingRepository {

    suspend fun createTraining()

    suspend fun getTraining()

    suspend fun deleteTraining()

    suspend fun editTraining()

}