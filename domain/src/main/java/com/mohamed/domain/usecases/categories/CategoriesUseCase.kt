package com.mohamed.domain.usecases.categories

import com.mohamed.domain.model.categories.CategoriesList
import com.mohamed.domain.repositories.categories.CategoriesRepo
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val categoriesRepo: CategoriesRepo,
) {
    suspend fun invoke(): List<CategoriesList>? {
        return categoriesRepo.categories()
    }
}