package com.project.fitx.data.datasource.training

import android.util.Log
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

            for (document in querySnapshot) {
                val name = document.getLong("nome")?.toInt() ?: 0
                val image = document.getString("imagem") ?: ""
                val observations = document.getString("obsercacoes") ?: ""
                val exercise =
                    Exercise(name = name, img = image.toUri(), observations = observations)
                Log.d("EXERCISE_LIST", "getExercises: $exercise")
                exerciseList.add(exercise)
            }

            Log.d("EXERCISE_LIST", "getExercises: $exerciseList")
            Resource.Success(exerciseList)
        } catch (e: Exception) {

            Resource.Error(e.message ?: "Error fetching exercises from Firestore")
        }
    }
}