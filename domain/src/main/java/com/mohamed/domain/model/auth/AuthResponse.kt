package com.mohamed.domain.model.auth

data class AuthResponse(
    val message: String,
    val user: User? = null,
    val token: String? = null,
)

data class User(
    val email: String? = null,
    val password: String? = null,
    val role: String? = null,
)