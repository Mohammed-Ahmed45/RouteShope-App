package com.mohamed.data.api.model.request

import com.google.gson.annotations.SerializedName
import com.mohamed.domain.model.auth.SignInRequest

data class SignInRequestDto(
    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("password")
    val password: String? = null,
) {
    companion object {
        fun toSignInRequest(request: SignInRequest): SignInRequestDto {
            return SignInRequestDto(
                email = request.email,
                password = request.password
            )
        }

    }

}