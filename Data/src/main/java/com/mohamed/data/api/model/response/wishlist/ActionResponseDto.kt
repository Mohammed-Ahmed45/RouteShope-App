package com.mohamed.data.api.model.response.wishlist

import com.google.gson.annotations.SerializedName
import com.mohamed.domain.model.wishlist.WishlistActionResponse

data class ActionResponseDto(

    @field:SerializedName("data")
    val data: List<String?>? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null,
) {
    fun toWishListAction(): WishlistActionResponse {

        return WishlistActionResponse(
            status = status,
            message = message,
            data = (data ?: emptyList()) as List<String>?
        )
    }
}