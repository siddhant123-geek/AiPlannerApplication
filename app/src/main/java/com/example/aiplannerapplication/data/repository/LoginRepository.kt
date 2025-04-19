package com.example.aiplannerapplication.data.repository

import android.util.Log
import com.example.aiplannerapplication.data.api.NetworkService
import com.example.aiplannerapplication.data.models.AuthState
import com.example.aiplannerapplication.data.models.Credentials
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(val networkService: NetworkService) {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    suspend fun login(credentials: Credentials): Flow<Response<Unit>> {
        return flow {
            emit(networkService.login(credentials))
        }
    }

    fun registerUser(credentials: Credentials): Flow<AuthState<FirebaseUser>> = flow {
        // First emit loading state
        emit(AuthState.Loading)

        try {
            // Perform registration on IO dispatcher
            val result = withContext(Dispatchers.IO) {
                val authResult = firebaseAuth.createUserWithEmailAndPassword(credentials.email, credentials.password).await()
                val user = authResult.user

                // Update profile if name is provided
                if (user != null && !(credentials.name.isNullOrBlank())) {
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(credentials.name)
                        .build()
                    user.updateProfile(profileUpdates).await()
                }

                user
            }

            // Emit success or error state
            if (result != null) {
                emit(AuthState.Authenticated(result))
            } else {
                emit(AuthState.Error("User registration failed"))
            }
        } catch (e: Exception) {
            emit(AuthState.Error(e.message ?: "Registration failed"))
        }
    }

    fun loginUser(credentials: Credentials): Flow<AuthState<FirebaseUser>> = flow {
        emit(AuthState.Loading)

        try {
            // Perform login on IO dispatcher
            val result = withContext(Dispatchers.IO) {
                val authResult = firebaseAuth.signInWithEmailAndPassword(credentials.email,
                                                                         credentials.email).await()
                authResult.user
            }

            // Emit success or error state
            if (result != null) {
                emit(AuthState.Authenticated(result))
            } else {
                emit(AuthState.Error("Login failed"))
            }
        } catch (e: Exception) {
            emit(AuthState.Error(e.message ?: "Login failed"))
        }
    }
}