package com.mohamed.data.api

import com.mohamed.data.api.model.request.SignInRequestDto
import com.mohamed.data.api.model.request.SignUpRequestDto

import com.mohamed.data.api.model.response.auth.AuthResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("v1/auth/signup")
    suspend fun userSignUp(@Body request: SignUpRequestDto): Response<AuthResponseDto>

    @POST("v1/auth/signin")
    suspend fun userSignIn(@Body request: SignInRequestDto): Response<AuthResponseDto>


}