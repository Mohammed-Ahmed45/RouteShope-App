package com.mohamed.data.repositories.brands

import com.mohamed.data.api.ApiServices
import com.mohamed.domain.model.brands.BrandsList
import com.mohamed.domain.repositories.brands.BrandsRepo
import javax.inject.Inject

class BrandsRepoImp @Inject constructor(
    private val apiServices: ApiServices,
) : BrandsRepo {
    override suspend fun brands(): List<BrandsList>? {
        return apiServices.getBrands().body()?.data?.map { brandsListDto ->
            brandsListDto.toBrandsList()
        }
    }
}