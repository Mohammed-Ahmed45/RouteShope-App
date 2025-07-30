package com.mohamed.data.api.model.request

import com.google.gson.annotations.SerializedName

data class ProductRequestDto(
    @SerializedName("productId")
    val productId: String? = null,
)