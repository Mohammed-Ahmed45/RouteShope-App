package com.mohamed.domain.usecases.auth

import com.mohamed.domain.model.auth.AuthResponse
import com.mohamed.domain.model.auth.SignUpRequest
import com.mohamed.domain.repositories.auth.SignUpRepo
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepo: SignUpRepo,
) {
    suspend fun invoke(request: SignUpRequest): AuthResponse? {
        return signUpRepo.userSignUp(request)
    }

}