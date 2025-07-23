package com.mohamed.domain.usecases.product

import com.mohamed.domain.model.product.ProductList
import com.mohamed.domain.repositories.product.ProductRepo
import jakarta.inject.Inject

class ProductUseCase @Inject constructor(
    private val productRepo: ProductRepo,
) {
    suspend fun invoke(): List<ProductList>? {
        return productRepo.product()
    }

}