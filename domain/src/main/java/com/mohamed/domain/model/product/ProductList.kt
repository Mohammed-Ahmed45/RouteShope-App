package com.mohamed.domain.model.product

data class ProductList(

    val sold: Int? = null,

    val images: List<String?>? = null,

    val quantity: Int? = null,

    val imageCover: String? = null,

    val description: String? = null,

    val title: String? = null,

    val ratingsQuantity: Int? = null,

    val ratingsAverage: Any? = null,

    val createdAt: String? = null,

    val reviews: List<Any?>? = null,

    val price: Int? = null,

    val v: Int? = null,


    val id: String? = null,


    val slug: String? = null,

    val updatedAt: String? = null,

    val priceAfterDiscount: Int? = null,

    )