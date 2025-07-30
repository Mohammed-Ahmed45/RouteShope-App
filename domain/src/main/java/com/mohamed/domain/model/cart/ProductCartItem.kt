package com.mohamed.domain.model.cart

import com.mohamed.domain.model.brands.BrandsList
import com.mohamed.domain.model.categories.CategoriesList

data class ProductCartItem(

    val id: String? = null,

    val title: String? = null,

    val quantity: Int? = null,

    val imageCover: String? = null,

    val subcategory: List<CategoriesList?>? = null,

    val category: CategoriesList? = null,

    val brand: BrandsList? = null,

    val ratingsAverage: Any? = null,
)