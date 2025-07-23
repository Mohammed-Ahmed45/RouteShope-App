package com.mohamed.data.api.model.response.auth

import com.mohamed.domain.model.auth.AuthResponse
import com.mohamed.domain.model.auth.User

data class AuthResponseDto(
    val message: String,
    val user: UserDto? = null,
    val token: String? = null,
) {
    fun toAuthResponse(): AuthResponse {
        return AuthResponse(
            message = message,
            user = user?.toUser(),
            token = token
        )
    }
}

data class UserDto(
    val email: String,
    val password: String,
    val role: String,
) {
    fun toUser(): User {
        return User(
            email = email,
            password = password,
            role = role
        )
    }
}