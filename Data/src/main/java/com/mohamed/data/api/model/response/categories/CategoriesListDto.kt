package com.mohamed.data.api.model.response.categories

import com.google.gson.annotations.SerializedName
import com.mohamed.domain.model.categories.CategoriesList

data class CategoriesListDto(

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
    fun toCategoriesList(): CategoriesList {
        return CategoriesList(
            id = id,
            image = image,
            name = name,
        )
    }

}