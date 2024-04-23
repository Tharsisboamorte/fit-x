package com.project.fitx.data.datasource.training

import com.project.fitx.data.Resource
import com.project.fitx.data.model.Exercise

interface ExerciseRepository {
    suspend fun getExercises(): Resource<List<Exercise>>

}