package com.example.aiplannerapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiplannerapplication.data.models.AuthState
import com.example.aiplannerapplication.data.models.Credentials
import com.example.aiplannerapplication.data.models.AuthResponse
import com.example.aiplannerapplication.data.repository.LoginRepository
import com.example.aiplannerapplication.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val networkHelper: NetworkHelper,
                                         val loginRepository: LoginRepository): ViewModel() {

    private val _loggedInState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loggedInState: StateFlow<Boolean> = _loggedInState

    private val _authState = MutableStateFlow<AuthState<AuthResponse>>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState<AuthResponse>> = _authState


    fun register(credentials: Credentials) {
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.registerUserCustom(credentials)
                .catch {
                    Log.d("###", "register: coming to catch in register with error as ${it.message}")
                    _authState.value = AuthState.Error(it.message ?: "Unknown error")
                }
                .collect { state ->
                _authState.value = AuthState.Authenticated(state)
            }
        }
    }

    fun login(credentials: Credentials) {
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.loginUserCustom(credentials)
                .catch {
                    Log.d("###", "login: coming to catch in login with error as ${it.message}")
                    _authState.value = AuthState.Error(it.message ?: "Unknown error")
                }
                .collect { state ->
                    _authState.value = AuthState.Authenticated(state)
                }
        }
    }

}