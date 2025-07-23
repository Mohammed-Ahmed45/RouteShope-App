package com.mohamed.routeshop.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.domain.model.categories.CategoriesList
import com.mohamed.domain.usecases.CategoriesUseCase
import com.mohamed.domain.usecases.SubCategoryUseCase
import com.mohamed.routeshop.ui.error.handleError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase,
    private val subCategoryUseCase: SubCategoryUseCase,
) : ViewModel() {
    val categoriesList = mutableStateListOf<CategoriesList>()
    val subCategoriesList = mutableStateListOf<CategoriesList>()

    fun getCategories() {
        viewModelScope.launch {
            try {

                categoriesList.clear()
                val response = categoriesUseCase.invoke()
                response?.let { categoriesList.addAll(it) }
                Log.d("CategoriesViewModel", "getCategories: ${categoriesList.size}")


            } catch (e: Exception) {
                Log.d("CategoriesViewModel", "getCategories: ${e.message}")
                handleError(e)
            }
        }
    }

    fun getSubCategory(categoryId: String) {
        viewModelScope.launch {
            try {
                subCategoriesList.clear()
                val response = subCategoryUseCase.invoke(categoryId)
                response?.let { subCategoriesList.addAll(it) }

            } catch (e: Exception) {
                Log.d("SubCategoryViewModel", "getSubCategory: ${e.message}")
                handleError(e)
            }
        }
    }

}