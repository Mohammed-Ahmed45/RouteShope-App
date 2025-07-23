package com.mohamed.data.api.model.request

import com.google.gson.annotations.SerializedName
import com.mohamed.domain.model.auth.SignUpRequest

data class SignUpRequestDto(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("rePassword")
    val rePassword: String? = null,
) {
    companion object {
        fun fromSignUpRequest(request: SignUpRequest): SignUpRequestDto {
            return SignUpRequestDto(
                name = request.name,
                email = request.email,
                phone = request.phone,
                password = request.password,
                rePassword = request.rePassword
            )
        }
    }
}