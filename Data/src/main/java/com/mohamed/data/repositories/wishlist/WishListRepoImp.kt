package com.mohamed.data.repositories.wishlist

import com.mohamed.data.api.ApiServices
import com.mohamed.data.api.model.request.ProductRequestDto
import com.mohamed.domain.model.product.ProductList
import com.mohamed.domain.repositories.wishlist.WishListRepo
import javax.inject.Inject

class WishListRepoImp @Inject constructor(
    private val apiServices: ApiServices,
) : WishListRepo {

    override suspend fun getWishList(): List<ProductList>? {

        val response = apiServices.getWishList()
        return if (response.isSuccessful) {
            response.body()?.data?.map { productListDto ->
                productListDto.toProductList()
            }
        } else {
            throw Exception("Failed to get wishlist: ${response.message()}")
        }
    }

    override suspend fun addToWishList(productId: String?): List<String?>? {
        // Check if user is logged in


        if (productId.isNullOrEmpty()) {
            throw IllegalArgumentException("ProductCartItemDto ID cannot be null or empty")
        }

        val response = apiServices.addToWishlist(

            productId = ProductRequestDto(productId = productId)
        )
        return if (response.isSuccessful) {
            response.body()?.data
        } else {
            throw Exception("Failed to add to wishlist: ${response.message()}")
        }
    }

    override suspend fun deleteWishList(productId: String?): List<String?>? {
        val response = apiServices.deleteWishList(productId = productId)
        return if (response.isSuccessful) {
            response.body()?.data
        } else {
            throw Exception("Failed to remove from wishlist: ${response.message()}")

        }

    }

}