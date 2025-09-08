package com.mohamed.routeshop.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.domain.model.cart.DataCart
import com.mohamed.domain.usecases.cart.CartUseCase
import com.mohamed.routeshop.ui.error.handleError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase,
) : ViewModel() {

    var cartListProductIds = mutableStateListOf<String>()
    var isLoading by mutableStateOf(false)
    var successMessage by mutableStateOf<String?>(null)
    var errorMessage by mutableStateOf<String?>(null)

    private val _cartData = MutableStateFlow<DataCart?>(null)
    val cartData: StateFlow<DataCart?> = _cartData.asStateFlow()


    private var dataVersion = 0

    fun getCartList() {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = cartUseCase.getCart()
                if (result != null) {
                    dataVersion++
                    _cartData.value = result.copy(
                        products = result.products.map { product ->
                            product.copy()
                        }
                    )
                } else {
                    errorMessage = "Failed to load cart"
                }

            } catch (e: Exception) {
                handleError(e)
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun addToCart(productId: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                cartUseCase.addToCart(productId)

                if (!cartListProductIds.contains(productId)) {
                    cartListProductIds.add(productId)
                }

                refreshCartData()

            } catch (e: Exception) {
                handleError(e)
                errorMessage = "Failed to add product to cart: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun deleteFromCart(productId: String) {
        viewModelScope.launch {
            try {
                errorMessage = null
                cartUseCase.deleteFromCart(productId)
                val currentCart = _cartData.value
                currentCart?.let { cart ->
                    val updatedProducts = cart.products.filter {
                        it.product?.id != productId
                    }
                    val updatedCart = cart.copy(
                        products = updatedProducts,
                        numOfCartItems = cart.numOfCartItems?.let { maxOf(0, it - 1) },
                        totalCartPrice = cart.totalCartPrice?.minus(
                            (cart.products.find { it.product?.id == productId }?.price ?: 0)
                        )
                    )
                    _cartData.value = updatedCart
                }

                refreshCartData()

            } catch (e: Exception) {
                refreshCartData()
                errorMessage = "Failed to remove product from cart: ${e.message}"
                handleError(e)
            } finally {
                isLoading = false
            }
        }
    }

    private suspend fun refreshCartData() {
        try {
            val result = cartUseCase.getCart()
            result?.let { freshData ->
                dataVersion++
                _cartData.value = freshData
            }
        } catch (e: Exception) {
            handleError(e)
        }
    }

//    private fun showSuccessMessage(message: String) {
//        viewModelScope.launch {
//            successMessage = message
//            delay(3000)
//            successMessage = null
//        }
//    }
}