package com.example.speakit.model

data class RegisterRequest (
    val userName: String,
    val email: String,
    val password: String
)

data class UserResponse(
    val id: String,
    val userName: String,
    val email: String
)


