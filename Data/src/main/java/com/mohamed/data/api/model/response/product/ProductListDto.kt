package com.mohamed.data.api.model.response.product

import com.google.gson.annotations.SerializedName
import com.mohamed.data.api.model.response.brands.BrandsListDto
import com.mohamed.data.api.model.response.categories.CategoriesListDto
import com.mohamed.domain.model.product.ProductList

data class ProductListDto(

    @field:SerializedName("sold")
    val sold: Int? = null,

    @field:SerializedName("images")
    val images: List<String?>? = null,

    @field:SerializedName("quantity")
    val quantity: Int? = null,

    @field:SerializedName("availableColors")
    val availableColors: List<Any?>? = null,

    @field:SerializedName("imageCover")
    val imageCover: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("ratingsQuantity")
    val ratingsQuantity: Int? = null,

    @field:SerializedName("ratingsAverage")
    val ratingsAverage: Any? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("subcategory")
    val subcategory: List<SubcategoryItem?>? = null,

    @field:SerializedName("category")
    val category: CategoriesListDto? = null,

    @field:SerializedName("brand")
    val brand: BrandsListDto? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null,

    @field:SerializedName("priceAfterDiscount")
    val priceAfterDiscount: Int? = null,
) {
    fun toProductList(): ProductList {
        return ProductList(
            sold = sold,
            images = images,
            quantity = quantity,
            imageCover = imageCover,
            description = description,
            title = title,
            ratingsQuantity = ratingsQuantity,
            ratingsAverage = ratingsAverage,
            createdAt = createdAt,
            price = price,
            id = id,
            slug = slug,
            updatedAt = updatedAt,
            priceAfterDiscount = priceAfterDiscount

        )
    }
}