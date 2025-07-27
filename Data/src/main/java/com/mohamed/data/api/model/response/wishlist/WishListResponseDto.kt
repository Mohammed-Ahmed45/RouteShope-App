package com.mohamed.data.api.model.response.wishlist

import com.google.gson.annotations.SerializedName
import com.mohamed.data.api.model.response.product.ProductListDto

data class WishListResponseDto(

    @field:SerializedName("data")
    val data: List<ProductListDto>? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("status")
    val status: String? = null,
)