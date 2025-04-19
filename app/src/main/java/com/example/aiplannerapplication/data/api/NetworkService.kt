package com.example.aiplannerapplication.data.api

import com.example.aiplannerapplication.data.models.Credentials
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkService {

    @POST("login")
    suspend fun login(@Body credentials: Credentials): Response<Unit>

}