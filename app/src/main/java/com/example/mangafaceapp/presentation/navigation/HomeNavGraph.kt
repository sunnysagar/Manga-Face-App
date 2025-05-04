package com.example.mangafaceapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mangafaceapp.presentation.ui.FaceRecognitionScreen
import com.example.mangafaceapp.presentation.ui.MangaScreen

@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier = Modifier){
    NavHost(navController = navController, startDestination = BottomNavItem.Manga.route, modifier = modifier){
        composable(BottomNavItem.Manga.route){
            MangaScreen(navController)
        }
        composable(BottomNavItem.Face.route) {
            FaceRecognitionScreen(navController)
        }
    }
}