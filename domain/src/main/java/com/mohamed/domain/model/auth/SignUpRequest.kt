package com.mohamed.domain.model.auth

data class SignUpRequest(
    val name: String,

    val email: String,

    val phone: String,

    val password: String,

    val rePassword: String,
)