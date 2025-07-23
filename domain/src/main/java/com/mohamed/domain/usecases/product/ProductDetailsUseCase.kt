package com.mohamed.domain.usecases.product

import com.mohamed.domain.model.product.ProductList
import com.mohamed.domain.repositories.product.ProductDetailsRepo
import jakarta.inject.Inject

class ProductDetailsUseCase @Inject constructor(
    private val productDetailsRepo: ProductDetailsRepo,
) {
    suspend fun invoke(productId: String): ProductList? {
        return productDetailsRepo.productDetails(productId)
    }
}