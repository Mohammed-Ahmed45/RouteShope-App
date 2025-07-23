package com.mohamed.domain.model.auth

data class SignInRequest(
    val email: String,
    val password: String,
)
