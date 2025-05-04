package com.example.mangafaceapp.presentation.navigation

sealed class BottomNavItem(val route: String, val label: String) {
    object Manga : BottomNavItem("manga", "Manga")
    object Face : BottomNavItem("face", "Face")
}