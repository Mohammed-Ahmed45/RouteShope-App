package com.mohamed.routeshop.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.domain.model.product.ProductList
import com.mohamed.domain.usecases.wishlist.WishListUseCase
import com.mohamed.routeshop.ui.error.handleError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val wishListUseCase: WishListUseCase,
//    private val tokenManager: TokenManager,
) : ViewModel() {
    val wishlistProductIds = mutableStateListOf<String>()
    val wishListItems = mutableStateListOf<ProductList>()
    var successMessage by mutableStateOf<String?>(null)

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    fun getWishList() {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null
                val response = wishListUseCase.getWishlist()
                if (response?.isNotEmpty() == true) {
                    wishListItems.clear()
                    wishListItems.addAll(response)

                    wishlistProductIds.clear()
                    wishlistProductIds.addAll(response.map { it.id.orEmpty() })
                }
            } catch (e: Exception) {
                handleError(e)
            } finally {
                isLoading = false
            }
        }
    }

    fun addToWishList(productId: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null


                val response = wishListUseCase.addToWishlist(
                    productId = productId
                )

                if (!wishlistProductIds.contains(productId)) {
                    wishlistProductIds.add(productId)
                }

                successMessage = "Product added to wishlist successfully"
                delay(3000)
                successMessage = null

            } catch (e: Exception) {
                handleError(e)
                errorMessage = "Failed to add product to wishlist"
            }
        }
    }


    fun deleteFromWishList(productId: String) {
        viewModelScope.launch {
            try {

                val response = wishListUseCase.deleteFromWishlist(productId)

                if (response.isNullOrEmpty()) {
                    wishlistProductIds.remove(productId)

                    wishListItems.removeAll { item ->
                        item.id == productId
                    }

                }

                successMessage = "Product removed from wishlist successfully"
                delay(3000)
                successMessage = null


            } catch (e: Exception) {
                handleError(e)
                errorMessage = "Failed to add product to delete from wishlist"
            }
        }
    }
}