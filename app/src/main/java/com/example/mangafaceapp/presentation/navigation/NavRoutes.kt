package com.example.mangafaceapp.presentation.navigation

sealed class NavRoutes(val route: String) {
    object SignIn : NavRoutes("Sign_in")
    object Home : NavRoutes("home")
    object MangaDetail : NavRoutes("manga_detail/{id}"){
        fun createRoute(id: String) = "manga_detail/$id"
    }
}