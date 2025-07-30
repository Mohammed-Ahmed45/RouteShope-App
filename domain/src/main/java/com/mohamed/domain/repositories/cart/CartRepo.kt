package com.mohamed.domain.repositories.cart

import com.mohamed.domain.model.cart.DataCart


interface CartRepo {

    suspend fun getCartList(): DataCart?
    suspend fun addToCart(productId: String): String?
    suspend fun deleteFromCart(productId: String): String?

}