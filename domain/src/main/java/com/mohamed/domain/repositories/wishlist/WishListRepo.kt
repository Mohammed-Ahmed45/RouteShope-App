package com.mohamed.domain.repositories.wishlist

import com.mohamed.domain.model.product.ProductList

interface WishListRepo {
    suspend fun getWishList(): List<ProductList>?


    suspend fun addToWishList(productId: String?): List<String?>?

    suspend fun deleteWishList(productId: String?): List<String?>?

}