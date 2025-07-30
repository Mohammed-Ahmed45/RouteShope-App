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
) : ViewModel() {
    var wishlistProductIds = mutableStateListOf<String>()
    var wishListItems = mutableStateListOf<ProductList>()
    var successMessage by mutableStateOf<String?>(null)

//    private val _wishlistIds = MutableStateFlow<List<String>>(emptyList())


//    fun isProductInWishList(productId: String): Flow<Boolean> {
//        return _wishlistIds.map { ids ->
//            ids.contains(productId) || wishlistProductIds.contains(productId)
//        }
//    }

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun getWishList() {
        viewModelScope.launch {
            try {
                isLoading = true
                val response = wishListUseCase.getWishlist()
                if (response?.isNotEmpty() == true) {
                    wishListItems.clear()
                    wishListItems.addAll(response)

                    wishlistProductIds.clear()
                    wishlistProductIds.addAll(response.mapNotNull { it.id })

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

                wishListUseCase.addToWishlist(
                    productId = productId
                )

                if (!wishlistProductIds.contains(productId)) {
                    wishlistProductIds.add(productId)
                }

                successMessage = "ProductCartItemDto added to wishlist successfully"
                delay(3000)
                successMessage = null

            } catch (e: Exception) {
                handleError(e)
                errorMessage = "Failed to add product to wishlist"

                wishlistProductIds.remove(productId)
            } finally {
                isLoading = false
            }
        }
    }

    fun deleteFromWishList(productId: String) {
        viewModelScope.launch {
            try {
                errorMessage = null

                val response = wishListUseCase.deleteFromWishlist(productId)

                if (response.isNullOrEmpty()) {
                    wishlistProductIds.remove(productId)
                    wishListItems.removeAll { item ->
                        item.id == productId
                    }
                }

                delay(100)
                getWishList()
                successMessage = null

            } catch (e: Exception) {
                handleError(e)
                errorMessage = "Failed to delete product from wishlist"
                if (!wishlistProductIds.contains(productId)) {
                    wishlistProductIds.add(productId)
                }
            } finally {
                isLoading = false
            }
        }
    }

}