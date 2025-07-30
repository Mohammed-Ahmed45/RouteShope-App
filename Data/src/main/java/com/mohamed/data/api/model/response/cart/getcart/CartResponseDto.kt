package com.mohamed.data.api.model.response.cart.getcart

import com.google.gson.annotations.SerializedName
import com.mohamed.domain.model.cart.DataCart

data class CartResponseDto(
    @field:SerializedName("data")
    val data: DataCartDto? = null,

    @field:SerializedName("numOfCartItems")
    val numOfCartItems: Int? = null,

    @field:SerializedName("cartId")
    val cartId: String? = null,

    @field:SerializedName("status")
    val status: String? = null,
) {
    fun toCartResponse(): DataCart {
        return DataCart(
            cartOwner = data?.cartOwner,
            createdAt = data?.createdAt,
            totalCartPrice = data?.totalCartPrice,
            id = data?.id,
            products = data?.products?.mapNotNull { it?.toProductListItem() } ?: emptyList(),
            updatedAt = data?.updatedAt,
            numOfCartItems = numOfCartItems,
            cartId = cartId,
            status = status
        )
    }
}