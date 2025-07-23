package com.mohamed.data.api.model.response.subcategory

import com.google.gson.annotations.SerializedName

data class SubCategoryResponseDto(

    @field:SerializedName("metadata")
    val metadata: Metadata? = null,

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("results")
    val results: Int? = null,
)