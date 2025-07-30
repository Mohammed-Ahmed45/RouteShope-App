package com.mohamed.data.api.model.response.wishlist

import com.google.gson.annotations.SerializedName
import com.mohamed.data.api.model.response.subcategory.SubCategoryItem
import com.mohamed.domain.model.categories.CategoriesList

data class DataItem(

    @field:SerializedName("sold")
    val sold: Int? = null,

    @field:SerializedName("images")
    val images: List<String?>? = null,

    @field:SerializedName("quantity")
    val quantity: Int? = null,

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

    @field:SerializedName("__v")
    val v: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null,


    @field:SerializedName("subcategory")
    val subcategory: List<SubCategoryItem?>? = null,

    @field:SerializedName("category")
    val category: CategoriesList? = null,

    @field:SerializedName("brand")
    val brand: Brand? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null,
)