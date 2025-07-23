package com.mohamed.data.repositories.auth

import com.mohamed.data.api.ApiServices
import com.mohamed.data.api.model.request.SignUpRequestDto
import com.mohamed.domain.model.auth.AuthResponse
import com.mohamed.domain.model.auth.SignUpRequest
import com.mohamed.domain.repositories.auth.SignUpRepo
import javax.inject.Inject

class SignUpRepoImp @Inject constructor(
    private val apiServices: ApiServices,
) : SignUpRepo {
    override suspend fun userSignUp(request: SignUpRequest): AuthResponse? {
        val request = SignUpRequestDto.Companion.fromSignUpRequest(request)
        return apiServices.userSignUp(request).body()?.toAuthResponse()
    }
}