package com.mohamed.domain.usecases.cart

import com.mohamed.domain.model.cart.DataCart
import com.mohamed.domain.repositories.cart.CartRepo
import javax.inject.Inject

class CartUseCase @Inject constructor(
    private val cartRepo: CartRepo,
) {
    suspend fun getCart(): DataCart? {
        return cartRepo.getCartList()
    }

    suspend fun addToCart(productId: String): String? {
        return cartRepo.addToCart(productId)
    }

    suspend fun deleteFromCart(productId: String): String? {
        return cartRepo.deleteFromCart(productId)
    }
//    suspend fun deleteFromCart(productId: String): List<String?>? {
//        return cartRepo.deleteFromCartList(productId)
//    }
}