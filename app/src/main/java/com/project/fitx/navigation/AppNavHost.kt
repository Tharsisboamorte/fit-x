package com.project.fitx.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.fitx.presentation.auth.AuthViewModel
import com.project.fitx.presentation.auth.login.view.LoginView
import com.project.fitx.presentation.auth.register.view.SignUpView
import com.project.fitx.presentation.home.view.HomeView
import com.project.fitx.presentation.training.view.TrainingView

@Composable
fun AppNavHost(
    viewModel: AuthViewModel,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = "login",
    ) {
        composable("login") {
            LoginView(navController = navController, viewModel = viewModel)
        }
        composable("register") {
            SignUpView(navController = navController, viewModel = viewModel)
        }
        composable("home") {
            HomeView(navController = navController)
        }
        composable("details") {
            TrainingView(navController = navController, trainingTitle = "Title")
        }
    }
}