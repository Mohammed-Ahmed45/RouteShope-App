package com.mohamed.domain.model.cart

data class DataCart(
    val cartOwner: String?,
    val createdAt: String?,
    val totalCartPrice: Int?,
    val id: String?,
    val products: List<ProductsListItem>,
    val updatedAt: String?,
    val numOfCartItems: Int?,
    val cartId: String?,
    val status: String?,
)