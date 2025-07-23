package com.mohamed.domain.repositories.auth

import com.mohamed.domain.model.auth.AuthResponse
import com.mohamed.domain.model.auth.SignUpRequest

interface SignUpRepo {
    suspend fun userSignUp(request: SignUpRequest): AuthResponse?
}