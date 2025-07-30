package com.mohamed.data.api.model.response.cart.getcart

import com.google.gson.annotations.SerializedName
import com.mohamed.domain.model.cart.ProductCartItem

data class ProductCartItemDto(

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("quantity")
    val quantity: Int? = null,

    @field:SerializedName("imageCover")
    val imageCover: String? = null,

    @field:SerializedName("subcategory")
    val subcategory: List<SubcategoryItem?>? = null,

    @field:SerializedName("category")
    val category: Category? = null,

    @field:SerializedName("brand")
    val brand: Brand? = null,

    @field:SerializedName("ratingsAverage")
    val ratingsAverage: Any? = null,
) {

    fun toCartItem(): ProductCartItem {
        return ProductCartItem(
            id = id,
            title = title,
            quantity = quantity,
            imageCover = imageCover,
            ratingsAverage = ratingsAverage
        )
    }
}