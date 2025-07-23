package com.mohamed.domain.repositories.brands

import com.mohamed.domain.model.brands.BrandsList

interface BrandsRepo {
    suspend fun brands(): List<BrandsList>?
}