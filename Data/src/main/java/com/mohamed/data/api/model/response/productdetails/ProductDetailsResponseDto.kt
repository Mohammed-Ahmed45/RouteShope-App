package com.mohamed.data.api.model.response.productdetails

import com.google.gson.annotations.SerializedName
import com.mohamed.data.api.model.response.product.ProductListDto

data class ProductDetailsResponseDto(

    @field:SerializedName("data")
    val data: ProductListDto? = null,
)

