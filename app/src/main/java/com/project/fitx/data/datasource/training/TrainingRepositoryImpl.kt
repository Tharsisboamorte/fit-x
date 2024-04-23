package com.project.fitx.data.datasource.training

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.project.fitx.data.Resource
import com.project.fitx.data.model.Training
import com.project.fitx.utils.await
import java.time.Instant
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class TrainingRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : TrainingRepository {

    private val trainingCollection = firestore.collection("training")
    override suspend fun createTraining(description: String) {
        try {
            val date = Date.from(Instant.now())
            val id = UUID.randomUUID().variant()
            val training = Training(nome = id, descricao = description, data = date)
            trainingCollection.add(training).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getTraining(): Resource<List<Training>> {
        return try {
            val querySnapshot = trainingCollection.get().await()
            val trainingList = mutableListOf<Training>()

            for (document in querySnapshot.documents) {
                val name = document.getLong("nome")?.toInt() ?: 0
                val descr = document.getString("descricao") ?: ""
                val date = document.getDate("data") ?: Date()
                val training = Training(nome = name, descricao = descr, data = date)

                Log.d("TRAINING_LIST", "getTraining: $training")
                trainingList.add(training)
            }

            Resource.Success(trainingList)
        } catch (e: Exception) {

            e.printStackTrace()
            Resource.Error(e.message ?: "Error fetching exercises from Firestore")
        }
    }

    override suspend fun deleteTraining(trainingId: String) {
        try {
            trainingCollection.document(trainingId).delete().await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun editTraining(trainingId: String, updatedTraining: Training) {
        try {
            trainingCollection.document().set(updatedTraining)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}