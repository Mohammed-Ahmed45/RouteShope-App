package com.mohamed.domain.usecases

import com.mohamed.domain.model.categories.CategoriesList
import com.mohamed.domain.repositories.categories.SubCategoryRepo
import javax.inject.Inject

class SubCategoryUseCase @Inject constructor(
    private val subCategoryRepo: SubCategoryRepo,
) {
    suspend fun invoke(categoryId: String): List<CategoriesList>? {
        return subCategoryRepo.subCategory(categoryId)
    }
}