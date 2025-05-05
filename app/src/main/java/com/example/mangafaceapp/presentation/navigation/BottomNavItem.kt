package com.example.mangafaceapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Manga : BottomNavItem("manga", "Manga", Icons.Default.Book)
    object Face : BottomNavItem("face", "Face", Icons.Default.Face)
    object Logout: BottomNavItem("logout", "Logout", Icons.Default.ExitToApp)
}