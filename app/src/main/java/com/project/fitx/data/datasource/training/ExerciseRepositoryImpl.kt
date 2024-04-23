package com.project.fitx.data.datasource.training

import androidx.core.net.toUri
import com.google.firebase.firestore.FirebaseFirestore
import com.project.fitx.data.Resource
import com.project.fitx.data.model.Exercise
import com.project.fitx.utils.await
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ExerciseRepository {
    override suspend fun getExercises(): Resource<List<Exercise>> {
        val exercisesCollection = firestore.collection("exercise")

        return try {
            val querySnapshot = exercisesCollection.get().await()
            val exerciseList = mutableListOf<Exercise>()

            for (document in querySnapshot.documents) {
                val name = document.getLong("nome")?.toInt() ?: 0
                val image = document.getString("nome") ?: ""
                val observations = document.getString("nome") ?: ""
                val exercise =
                    Exercise(name = name, img = image.toUri(), observations = observations)
                exerciseList.add(exercise)
            }

            Resource.Success(exerciseList)
        } catch (e: Exception) {

            Resource.Error(e.message ?: "Error fetching exercises from Firestore")
        }
    }
}