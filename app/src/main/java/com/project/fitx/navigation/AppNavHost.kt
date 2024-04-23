package com.project.fitx.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
import com.project.fitx.utils.LoadingScreen

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
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(700)
            )
        }
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
            route = "details?title={title}&id={id}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = "Treinamento Padrão"
                },
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )) { backStackEntry ->
            TrainingView(
                navController = navController,
                trainingViewModel = trainingViewModel,
                homeViewModel = homeViewModel,
                title = backStackEntry.arguments?.getString("title")!!,
                trainingId = backStackEntry.arguments?.getString("id")!!
            )
        }
        composable("profile") {
            ProfileView(navController = navController, viewModel = authViewModel)
        }
        composable("loading") {
            LoadingScreen(navController = navController)
        }
    }
}