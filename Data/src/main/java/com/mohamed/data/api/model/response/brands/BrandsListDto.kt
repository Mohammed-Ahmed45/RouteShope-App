package com.mohamed.data.api.model.response.brands

import com.google.gson.annotations.SerializedName
import com.mohamed.domain.model.brands.BrandsList

data class BrandsListDto(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null,
) {
    fun toBrandsList(): BrandsList {
        return BrandsList(
            id = id,
            image = image,
            name = name,
        )

    }
}