package com.example.aiplannerapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiplannerapplication.data.models.AuthState
import com.example.aiplannerapplication.data.models.Credentials
import com.example.aiplannerapplication.data.repository.LoginRepository
import com.example.aiplannerapplication.utils.NetworkHelper
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val networkHelper: NetworkHelper,
                                         val loginRepository: LoginRepository): ViewModel() {

    private val _loggedInState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loggedInState: StateFlow<Boolean> = _loggedInState

    private val _authState = MutableStateFlow<AuthState<FirebaseUser>>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState<FirebaseUser>> = _authState


    fun register(credentials: Credentials) {
        viewModelScope.launch {
            loginRepository.registerUser(credentials).collect { state ->
                _authState.value = state
            }
        }
    }

    fun login(credentials: Credentials) {
        viewModelScope.launch {
            loginRepository.loginUser(credentials).collect { state ->
                _authState.value = state
            }
        }
    }

}