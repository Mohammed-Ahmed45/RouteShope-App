package com.mohamed.data.repositories.product

import com.mohamed.data.api.ApiServices
import com.mohamed.domain.model.product.ProductList
import com.mohamed.domain.repositories.product.ProductDetailsRepo
import jakarta.inject.Inject

class ProductDetailsRepoImp @Inject constructor(
    private val apiServices: ApiServices,
) : ProductDetailsRepo {
    override suspend fun productDetails(productId: String): ProductList? {
        return apiServices.getProductsDetails(productId).body()?.data?.toProductList()
    }
}