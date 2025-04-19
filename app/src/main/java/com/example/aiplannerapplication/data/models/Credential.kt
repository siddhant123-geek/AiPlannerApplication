package com.example.aiplannerapplication.data.models

import com.google.gson.annotations.SerializedName

data class Credentials(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("name")
    val name: String
)