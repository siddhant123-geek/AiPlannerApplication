package com.example.aiplannerapplication.data.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: AuthData
)

data class AuthData(
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("accessToken")
    val accessToken: String,
)

data class LoginResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: AuthData
)

data class CredRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
)