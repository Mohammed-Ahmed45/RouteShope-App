package com.mohamed.data.api.model.request

import com.google.gson.annotations.SerializedName

data class AddToWishlistRequestDto(
    @SerializedName("productId")
    val productId: String? = null,
) {
//    companion object {
//        fun toAddToWishListResponse(productId: AddToWishlistRequest): AddToWishlistRequestDto {
//            return AddToWishlistRequestDto(
//                productId = productId.productId
//            )
//        }
//    }

}