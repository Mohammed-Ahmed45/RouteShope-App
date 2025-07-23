package com.mohamed.domain.usecases

import com.mohamed.domain.model.brands.BrandsList
import com.mohamed.domain.repositories.brands.BrandsRepo
import javax.inject.Inject

class BrandsUseCase @Inject constructor(
    private val brandsRepo: BrandsRepo,
) {
    suspend fun invoke(): List<BrandsList>? {
        return brandsRepo.brands()
    }
}