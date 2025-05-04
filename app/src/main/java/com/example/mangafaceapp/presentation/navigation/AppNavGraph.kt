package com.example.mangafaceapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mangafaceapp.presentation.ui.HomeScreen
import com.example.mangafaceapp.presentation.ui.SignInScreen

@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = NavRoutes.SignIn.route){
       composable(NavRoutes.SignIn.route) {
           SignInScreen(
               navController,
               viewModel = TODO()
           )
       }
        composable(NavRoutes.Home.route) {
            HomeScreen(navController)
        }

    }

}