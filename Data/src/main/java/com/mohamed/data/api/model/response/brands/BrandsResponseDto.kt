package com.mohamed.data.api.model.response.brands

import com.google.gson.annotations.SerializedName
import com.mohamed.data.api.model.response.categories.Metadata

data class BrandsResponseDto(

    @field:SerializedName("metadata")
    val metadata: Metadata? = null,

    @field:SerializedName("data")
    val data: List<BrandsListDto>? = null,

    @field:SerializedName("results")
    val results: Int? = null,
)