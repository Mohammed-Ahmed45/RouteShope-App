package com.mohamed.data.repositories.product

import com.mohamed.data.api.ApiServices
import com.mohamed.domain.model.product.ProductList
import com.mohamed.domain.repositories.product.ProductRepo
import jakarta.inject.Inject

class ProductRepoImp @Inject constructor(
    private val apiServices: ApiServices,
) : ProductRepo {
    override suspend fun product(): List<ProductList>? {
        return apiServices.getProducts().body()?.data?.map { productListDto ->
            productListDto.toProductList()
        }
    }
}