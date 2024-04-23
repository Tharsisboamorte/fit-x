package com.project.fitx.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.fitx.data.datasource.auth.AuthRepository
import com.project.fitx.data.datasource.auth.AuthRepositoryImpl
import com.project.fitx.data.datasource.training.ExerciseRepository
import com.project.fitx.data.datasource.training.ExerciseRepositoryImpl
import com.project.fitx.data.datasource.training.TrainingRepository
import com.project.fitx.data.datasource.training.TrainingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun providesExerciseRepository(impl: ExerciseRepositoryImpl): ExerciseRepository = impl

    @Provides
    fun providesTrainingRepository(impl: TrainingRepositoryImpl): TrainingRepository = impl
}