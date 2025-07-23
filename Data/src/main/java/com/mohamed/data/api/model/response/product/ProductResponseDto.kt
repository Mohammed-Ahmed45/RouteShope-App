package com.mohamed.data.api.model.response.product

import com.google.gson.annotations.SerializedName

data class ProductResponseDto(

    @field:SerializedName("metadata")
    val metadata: Metadata? = null,

    @field:SerializedName("data")
    val data: List<ProductListDto>? = null,

    @field:SerializedName("results")
    val results: Int? = null,
)