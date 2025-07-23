package com.mohamed.domain.repositories.auth

import com.mohamed.domain.model.auth.AuthResponse
import com.mohamed.domain.model.auth.SignInRequest

interface SignInRepo {
    suspend fun userSignIn(request: SignInRequest): AuthResponse?
}