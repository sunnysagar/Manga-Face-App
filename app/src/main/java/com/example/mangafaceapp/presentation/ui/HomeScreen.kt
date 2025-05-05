package com.example.mangafaceapp.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mangafaceapp.presentation.navigation.BottomNavItem
import com.example.mangafaceapp.presentation.navigation.HomeNavGraph

@Composable
fun HomeScreen(
    navController: NavController,
    signInViewModel: SignInViewModel = hiltViewModel()
){

    val bottomNavController = rememberNavController()
    val items = listOf(BottomNavItem.Manga, BottomNavItem.Face, BottomNavItem.Logout)

    Scaffold(
        bottomBar = {
            NavigationBar {
               val currentDestination = bottomNavController.currentBackStackEntryAsState().value?.destination
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination?.route == item.route,
                        onClick = {
                            if(item is BottomNavItem.Logout){
                                signInViewModel.logout()
                                navController.navigate("sign-in"){
                                    popUpTo(0)
                                }
                            } else {
                                bottomNavController.navigate(item.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(bottomNavController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }
                            }
                        },
                        label = { Text(item.label) },
                        icon = { Icon(imageVector = item.icon, contentDescription = item.label) }
                    )
                }
            }
        }
    ) { padding ->
        HomeNavGraph(navController = bottomNavController, modifier = Modifier.padding(padding))
    }

}