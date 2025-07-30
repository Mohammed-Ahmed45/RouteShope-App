package com.mohamed.data.api.model.response.cart.addtocart

import com.google.gson.annotations.SerializedName

data class ProductsItem(

    @field:SerializedName("product")
    val product: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null,
)