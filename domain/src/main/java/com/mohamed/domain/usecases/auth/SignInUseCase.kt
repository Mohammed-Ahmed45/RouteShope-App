package com.mohamed.domain.usecases.auth

import com.mohamed.domain.model.auth.AuthResponse
import com.mohamed.domain.model.auth.SignInRequest
import com.mohamed.domain.repositories.auth.SignInRepo
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signInRepo: SignInRepo,
) {
    suspend fun invoke(request: SignInRequest): AuthResponse? {
        return signInRepo.userSignIn(request)
    }
}