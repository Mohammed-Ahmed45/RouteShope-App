package com.mohamed.data.api.model.response.cart.getcart

import com.google.gson.annotations.SerializedName

data class DataCartDto(

    @field:SerializedName("cartOwner")
    val cartOwner: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("totalCartPrice")
    val totalCartPrice: Int? = null,

    @field:SerializedName("__v")
    val v: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("products")
    val products: List<ProductsListItemDto?>? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null,
)