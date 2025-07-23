package com.mohamed.data.api.model.response.categories

import com.google.gson.annotations.SerializedName

data class CategoriesResponseDto(

    @field:SerializedName("metadata")
    val metadata: Metadata? = null,

    @field:SerializedName("data")
    val data: List<CategoriesListDto>? = null,

    @field:SerializedName("results")
    val results: Int? = null,
)