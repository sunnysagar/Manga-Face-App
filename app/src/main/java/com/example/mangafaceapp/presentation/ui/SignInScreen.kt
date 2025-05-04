package com.example.mangafaceapp.presentation.ui

import androidx.compose.runtime.getValue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mangafaceapp.presentation.navigation.NavRoutes

@Composable
fun SignInScreen(navController: NavHostController, viewModel: SignInViewModel= hiltViewModel()){
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val uiState by viewModel.uiState

    if(uiState.isSignedIn){
        // Navigate to Home Screen after sign - in
        LaunchedEffect(Unit) {
            navController.navigate(NavRoutes.Home.route){
                popUpTo(NavRoutes.SignIn.route){ inclusive = true}
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.signIn(emailState.value, passwordState.value) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sign In")
        }

        if (!uiState.isSignedIn && emailState.value.isNotBlank()) {
            Text(
                text = "Invalid credentials, please try again.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

}