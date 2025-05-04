package com.example.mangafaceapp.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mangafaceapp.presentation.navigation.BottomNavItem

@Composable
fun HomeScreen(navController: NavController){

    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = listOf(BottomNavItem.Manga, BottomNavItem.Face)
                items.forEach { item ->
                    NavigationBarItem(
                        selected = bottomNavController.currentDestination?.route == item.route,
                        onClick = {
                            bottomNavController.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(bottomNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                            }
                        },
                        label = { Text(item.label) },
                        icon = { Icon(imageVector = Icons.Default.Home, contentDescription = item.label) }
                    )
                }
            }
        }
    ) { padding ->
        HomeNavGraph(navController = bottomNavController, modifier = Modifier.padding(padding))
    }

}