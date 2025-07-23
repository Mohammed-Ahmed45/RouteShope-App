package com.mohamed.domain.repositories.product

import com.mohamed.domain.model.product.ProductList

interface ProductRepo {
    suspend fun product(): List<ProductList>?
}