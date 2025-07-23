package com.mohamed.data.repositories.auth

import com.mohamed.data.api.ApiServices
import com.mohamed.data.api.model.request.SignInRequestDto
import com.mohamed.domain.model.auth.AuthResponse
import com.mohamed.domain.model.auth.SignInRequest
import com.mohamed.domain.repositories.auth.SignInRepo
import javax.inject.Inject

class SignInRepoImp @Inject constructor(
    private val apiServices: ApiServices,
) : SignInRepo {
    override suspend fun userSignIn(request: SignInRequest): AuthResponse? {
        val request = SignInRequestDto.toSignInRequest(request)
        return apiServices.userSignIn(request).body()?.toAuthResponse()
    }
}