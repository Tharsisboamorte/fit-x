package com.project.fitx.presentation.training.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.fitx.presentation.training.components.TrainingTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrainingView(
    navController: NavController,
    trainingTitle: String
) {
    Scaffold(
        topBar = {
            TrainingTopBar(
                onBackAction = { navController.navigate("home") },
                title = trainingTitle,
            )
        }
    ) {

    }
}

@Preview
@Composable
fun PreviewTraining() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        TrainingView(navController = rememberNavController(), trainingTitle = "Treinando ta dificil demais caralho")
    }
}