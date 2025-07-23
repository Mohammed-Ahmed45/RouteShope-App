package com.mohamed.domain.repositories.categories

import com.mohamed.domain.model.categories.CategoriesList

interface SubCategoryRepo {
    suspend fun subCategory(categoryId: String): List<CategoriesList>?
}