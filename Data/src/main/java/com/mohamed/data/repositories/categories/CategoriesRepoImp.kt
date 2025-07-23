package com.mohamed.data.repositories.categories

import com.mohamed.data.api.ApiServices
import com.mohamed.domain.model.categories.CategoriesList
import com.mohamed.domain.repositories.categories.CategoriesRepo
import javax.inject.Inject

class CategoriesRepoImp @Inject constructor(
    private val apiServices: ApiServices,
) : CategoriesRepo {
    override suspend fun categories(): List<CategoriesList>? {
        return apiServices.getCategories().body()?.data?.map { categoriesListDto ->
            categoriesListDto.toCategoriesList()
        }
    }

}