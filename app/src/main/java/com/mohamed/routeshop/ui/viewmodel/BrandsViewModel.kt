package com.mohamed.routeshop.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.domain.model.brands.BrandsList
import com.mohamed.domain.usecases.BrandsUseCase
import com.mohamed.routeshop.ui.error.handleError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
    private val brandsUseCase: BrandsUseCase,
) : ViewModel() {

    val brandsList = mutableStateListOf<BrandsList>()
    fun getBrands() {
        viewModelScope.launch {
            try {
                val response = brandsUseCase.invoke()
                response?.let { brandsList.addAll(it) }

            } catch (e: Exception) {
                Log.d("CategoriesViewModel", "getBrands: ${e.message}")
                handleError(e)
            }
        }
    }


}