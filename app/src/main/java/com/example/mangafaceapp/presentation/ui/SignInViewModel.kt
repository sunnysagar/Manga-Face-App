package com.example.mangafaceapp.presentation.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangafaceapp.data.User
import com.example.mangafaceapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _uiState = mutableStateOf(SignInUiState())
    val uiState: State<SignInUiState> = _uiState

    fun signIn(email: String, password: String){
        viewModelScope.launch {
            val user = userRepository.getUserByEmail(email)
            if (user == null){
                // User does not exist, create a new account
                userRepository.insertUser(User(email=email, password = password))
                _uiState.value = _uiState.value.copy(isSignedIn = true)
            } else if(user.password == password){
                // User exists and password matches
                _uiState.value = _uiState.value.copy(isSignedIn = true)
            } else {
                // Invalid credentials
                _uiState.value = _uiState.value.copy(isSignedIn = false)
            }
        }
    }

}

data class SignInUiState(val isSignedIn: Boolean = false)