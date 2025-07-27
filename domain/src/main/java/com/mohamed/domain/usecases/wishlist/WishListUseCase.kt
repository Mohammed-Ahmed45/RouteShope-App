package com.mohamed.domain.usecases.wishlist

import com.mohamed.domain.model.product.ProductList
import com.mohamed.domain.repositories.wishlist.WishListRepo
import javax.inject.Inject

class WishListUseCase @Inject constructor(
    private val wishListRepo: WishListRepo,
) {
    suspend fun getWishlist(): List<ProductList>? {
        return wishListRepo.getWishList()
    }

    suspend fun addToWishlist(productId: String?): List<String?>? {
        return wishListRepo.addToWishList(productId = productId)
    }

    suspend fun deleteFromWishlist(productId: String?): List<String?>? {
        return wishListRepo.deleteWishList(productId = productId)
    }
}