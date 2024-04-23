package com.project.fitx.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.fitx.presentation.auth.AuthViewModel
import com.project.fitx.presentation.auth.login.view.LoginView
import com.project.fitx.presentation.auth.register.view.SignUpView
import com.project.fitx.presentation.home.HomeViewModel
import com.project.fitx.presentation.home.view.HomeView
import com.project.fitx.presentation.profile.ProfileView
import com.project.fitx.presentation.training.TrainingViewModel
import com.project.fitx.presentation.training.view.TrainingView

@Composable
fun AppNavHost(
    authViewModel: AuthViewModel,
    homeViewModel: HomeViewModel,
    trainingViewModel: TrainingViewModel,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = "login",
    ) {
        composable("login") {
            LoginView(navController = navController, viewModel = authViewModel)
        }
        composable("register") {
            SignUpView(navController = navController, viewModel = authViewModel)
        }
        composable("home") {
            HomeView(navController = navController, viewModel = homeViewModel)
        }
        composable(
            route = "details?title={title}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = "Treinamento Padrão"
                }
            )) { backStackEntry ->
            backStackEntry.arguments?.getString("title")?.let {
                TrainingView(
                    navController = navController,
                    viewModel = trainingViewModel,
                    title = it
                )
            }
        }
        composable("profile") {
            ProfileView(navController = navController, viewModel = authViewModel)
        }
    }
}