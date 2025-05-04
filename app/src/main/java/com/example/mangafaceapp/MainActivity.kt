package com.example.mangafaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.mangafaceapp.presentation.navigation.AppNavGraph
import com.example.mangafaceapp.presentation.ui.SignInViewModel
import com.example.mangafaceapp.ui.theme.MangaFaceAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MangaFaceAppTheme {
                val navController = rememberNavController()
                val viewModel: SignInViewModel = hiltViewModel()

                val isLoggedIn by viewModel.isLoggedIn.collectAsState(initial = false)

                LaunchedEffect(isLoggedIn) {
                    if (isLoggedIn) {
                        navController.navigate("home") {
                            popUpTo("splash") { inclusive = true }
                        }
                    } else {
                        navController.navigate("sign-in") {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                }
                AppNavGraph(navController = navController)
                }
            }
        }
    }



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MangaFaceAppTheme {
        Greeting("Android")
    }
}