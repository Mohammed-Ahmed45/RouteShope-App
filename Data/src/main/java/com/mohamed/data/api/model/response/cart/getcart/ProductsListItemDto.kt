package com.mohamed.data.api.model.response.cart.getcart

import com.google.gson.annotations.SerializedName
import com.mohamed.domain.model.cart.ProductsListItem

data class ProductsListItemDto(

    @field:SerializedName("product")
    val product: ProductCartItemDto? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null,
) {
    fun toProductListItem(): ProductsListItem {
        return ProductsListItem(
            product = product?.toCartItem(),
            price = price,
            count = count,
            id = id
        )
    }
}