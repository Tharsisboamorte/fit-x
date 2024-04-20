package com.project.fitx.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.fitx.presentation.auth.AuthViewModel
import com.project.fitx.presentation.auth.login.view.LoginView

@Composable
fun AppNavHost(
    viewModel: AuthViewModel,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = "login",
    ) {
        composable("login"){
            LoginView(navController = navController)
        }
        composable("register"){
            //TODO: (Add instance of signUpPage)
        }
        composable("home"){
            //TODO: (Add instance of homePage)
        }
        composable("details"){
            //TODO: (Add instance of trainingPage)
        }
    }
}