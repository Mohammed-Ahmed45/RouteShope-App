package com.mohamed.data.repositories.cart

import com.mohamed.data.api.ApiServices
import com.mohamed.data.api.model.request.ProductRequestDto
import com.mohamed.domain.model.cart.DataCart
import com.mohamed.domain.repositories.cart.CartRepo
import javax.inject.Inject

class CartRepoImp @Inject constructor(
    private val apiServices: ApiServices,
) : CartRepo {
//    override suspend fun addToCart(productId: String?): List<String?>? {
//        return apiServices.addToCart(productId = ProductRequestDto(productId)).body()?.data
//    }


    //    override suspend fun deleteFromCartList(productId: String?): List<String?>? {
//        return apiServices.deleteFromCartList(productId = productId).body()?.data
//    }
    override suspend fun getCartList(): DataCart? {
        return apiServices.getCartList().body()?.toCartResponse()
    }

    override suspend fun addToCart(productId: String): String? {
        return try {
            val request = ProductRequestDto(productId = productId)
            val response = apiServices.addToCart(request)
            if (response.isSuccessful) {
                response.body()?.message ?: "Added to cart successfully"
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun deleteFromCart(productId: String): String? {
        return try {
            val response = apiServices.deleteFromCart(productId)
            if (response.isSuccessful) {
                response.body()?.message
            } else {
                throw Exception("Failed to remove from wishlist: ${response.message()}")
            }
        } catch (e: Exception) {
            null
        }
    }
}