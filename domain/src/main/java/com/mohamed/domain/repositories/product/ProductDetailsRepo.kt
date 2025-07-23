package com.mohamed.domain.repositories.product

import com.mohamed.domain.model.product.ProductList

interface ProductDetailsRepo {
    suspend fun productDetails(productId: String): ProductList?
}