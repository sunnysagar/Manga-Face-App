package com.example.mangafaceapp.presentation.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mangafaceapp.data.model.User
import com.example.mangafaceapp.data.local.UserPreferences
import com.example.mangafaceapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreferences: UserPreferences
): ViewModel() {
    private val _uiState = mutableStateOf(SignInUiState())
    val uiState: State<SignInUiState> = _uiState

    val isLoggedIn = userPreferences.isLoggedIn

    fun signIn(email: String, password: String){
        viewModelScope.launch {
            val user = userRepository.getUserByEmail(email)
            if (user == null){
                // User does not exist, create a new account
                userRepository.insertUser(User(email=email, password = password))
                _uiState.value = _uiState.value.copy(isSignedIn = true)
            } else if( user != null && user.password == password){
                // User exists and password matches
                userPreferences.saveLoginState(email)
                _uiState.value = _uiState.value.copy(isSignedIn = true)
            } else {
                // Invalid credentials
                _uiState.value = _uiState.value.copy(isSignedIn = false)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.clearLoginState()
        }
    }

}

data class SignInUiState(val isSignedIn: Boolean = false)