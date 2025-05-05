package com.example.mangafaceapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mangafaceapp.presentation.face.FaceRecognitionScreen
import com.example.mangafaceapp.presentation.manga.MangaDetailScreen
import com.example.mangafaceapp.presentation.manga.MangaScreen

@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier = Modifier){
    NavHost(navController = navController, startDestination = BottomNavItem.Manga.route, modifier = modifier){
        composable(BottomNavItem.Manga.route){
            MangaScreen(navController)
        }
        composable(BottomNavItem.Face.route) {
            FaceRecognitionScreen(navController)
        }

        // Adding this block to handle the Manga Detail screen
        composable("mangaDetail/{mangaId}") { backStackEntry ->
            val mangaId = backStackEntry.arguments?.getString("mangaId") ?: ""
            MangaDetailScreen(mangaId = mangaId, navController = navController)
        }
    }
}