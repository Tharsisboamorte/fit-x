package com.project.fitx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.project.fitx.navigation.AppNavHost
import com.project.fitx.presentation.auth.AuthViewModel
import com.project.fitx.presentation.home.HomeViewModel
import com.project.fitx.presentation.training.TrainingViewModel
import com.project.fitx.ui.theme.FitxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authViewModel by viewModels<AuthViewModel>()
    private val trainingViewModel by viewModels<TrainingViewModel>()
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitxTheme {
                AppNavHost(
                    authViewModel = authViewModel,
                    trainingViewModel = trainingViewModel,
                    homeViewModel = homeViewModel
                )
            }
        }
    }
}