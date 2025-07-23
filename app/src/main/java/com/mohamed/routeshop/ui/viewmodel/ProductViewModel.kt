package com.mohamed.routeshop.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.domain.model.product.ProductList
import com.mohamed.domain.usecases.product.ProductDetailsUseCase
import com.mohamed.domain.usecases.product.ProductUseCase
import com.mohamed.routeshop.ui.error.handleError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val productDetailsUseCase: ProductDetailsUseCase,
) : ViewModel() {
    val productList = mutableStateListOf<ProductList>()
    val productDetailsList = mutableStateListOf<ProductList>()

    fun getProduct() {
        viewModelScope.launch {
            try {
                productList.clear()
                val response = productUseCase.invoke()
                response?.let { productList.addAll(it) }
            } catch (e: Exception) {
                Log.d("ProductViewModel", "getProduct: ${e.message}")
                handleError(e)
            }
        }
    }

    fun getProductDetails(productId: String) {
        viewModelScope.launch {
            try {
                productDetailsList.clear()
                val response = productDetailsUseCase.invoke(productId)
                response?.let { productDetailsList.add(it) }
            } catch (e: Exception) {
                Log.d("ProductDetailsViewModel", "getProduct: ${e.message}")
                handleError(e)
            }
        }


    }
}