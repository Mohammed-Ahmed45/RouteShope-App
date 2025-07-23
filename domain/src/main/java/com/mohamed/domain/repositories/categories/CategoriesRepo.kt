package com.mohamed.domain.repositories.categories

import com.mohamed.domain.model.categories.CategoriesList

interface CategoriesRepo {
    suspend fun categories(): List<CategoriesList>?
}