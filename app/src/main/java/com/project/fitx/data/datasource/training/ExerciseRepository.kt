package com.project.fitx.data.datasource.training

interface ExerciseRepository {
    suspend fun getExercises()
}