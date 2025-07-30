package com.mohamed.data.api.model.response.cart.addtocart

import com.google.gson.annotations.SerializedName

data class ActionCartResponseDto(

    @field:SerializedName("data")
    val data: DataDTo? = null,

    @field:SerializedName("numOfCartItems")
    val numOfCartItems: Int? = null,

    @field:SerializedName("cartId")
    val cartId: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null,
)