package com.example.aiplannerapplication.data.api

import com.example.aiplannerapplication.data.models.CredRequest
import com.example.aiplannerapplication.data.models.LoginResponse
import com.example.aiplannerapplication.data.models.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkService {

    @POST("user/login")
    suspend fun login(@Body loginRequest: CredRequest): AuthResponse

    @POST("user/register")
    suspend fun register(@Body loginRequest: CredRequest): AuthResponse

}