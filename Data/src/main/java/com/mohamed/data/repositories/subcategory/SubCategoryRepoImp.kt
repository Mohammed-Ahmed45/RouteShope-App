package com.mohamed.data.repositories.subcategory

import com.mohamed.data.api.ApiServices
import com.mohamed.domain.model.categories.CategoriesList
import com.mohamed.domain.repositories.categories.SubCategoryRepo
import javax.inject.Inject

class SubCategoryRepoImp @Inject constructor(
    private val apiServices: ApiServices,
) : SubCategoryRepo {
    override suspend fun subCategory(categoryId: String): List<CategoriesList>? {
        return apiServices.getSubCategory(categoryId = categoryId)
            .body()?.data?.map { subCategoryDto ->
                subCategoryDto.toCategoriesList()
            }
    }
}
