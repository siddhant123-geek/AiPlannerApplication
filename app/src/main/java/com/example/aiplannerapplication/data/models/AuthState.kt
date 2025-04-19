package com.example.aiplannerapplication.data.models

import com.google.firebase.auth.FirebaseUser

sealed interface AuthState<out T> {
    object Loading : AuthState<Nothing>
    object Unauthenticated : AuthState<Nothing>
    data class Authenticated<T>(val data: T) : AuthState<T>
    data class Error(val error: String) : AuthState<Nothing>
}