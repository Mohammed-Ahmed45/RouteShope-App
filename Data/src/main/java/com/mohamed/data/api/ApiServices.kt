package com.mohamed.data.api

import com.mohamed.data.api.model.request.AddToWishlistRequestDto
import com.mohamed.data.api.model.request.SignInRequestDto
import com.mohamed.data.api.model.request.SignUpRequestDto
import com.mohamed.data.api.model.response.auth.AuthResponseDto
import com.mohamed.data.api.model.response.brands.BrandsResponseDto
import com.mohamed.data.api.model.response.categories.CategoriesResponseDto
import com.mohamed.data.api.model.response.product.ProductResponseDto
import com.mohamed.data.api.model.response.productdetails.ProductDetailsResponseDto
import com.mohamed.data.api.model.response.wishlist.WishListActionResponseDto
import com.mohamed.data.api.model.response.wishlist.WishListResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {

    val token: String
        get() = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY4ODIyN2JhNTdlNzhkMWU0YjAxMzMxNiIsIm5hbWUiOiJNb2hhbWVkIEFobWVkIiwicm9sZSI6InVzZXIiLCJpYXQiOjE3NTMzNzA5ODksImV4cCI6MTc2MTE0Njk4OX0.Y1pysDGrNQwuZYTymhCgSovGomA623IsdKF7IWr__No"

    @POST("v1/auth/signup")
    suspend fun userSignUp(@Body request: SignUpRequestDto): Response<AuthResponseDto>

    @POST("v1/auth/signin")
    suspend fun userSignIn(@Body request: SignInRequestDto): Response<AuthResponseDto>

    @GET("v1/categories")
    suspend fun getCategories(): Response<CategoriesResponseDto>

    @GET("v1/brands")
    suspend fun getBrands(): Response<BrandsResponseDto>

    @GET("v1/categories/{categoryId}/subcategories")
    suspend fun getSubCategory(@Path("categoryId") categoryId: String): Response<CategoriesResponseDto>


    @GET("v1/products")
    suspend fun getProducts(): Response<ProductResponseDto>

    @GET("v1/products/{productId}")
    suspend fun getProductsDetails(@Path("productId") productId: String): Response<ProductDetailsResponseDto>

    //wishList
    @GET("v1/wishlist")
    suspend fun getWishList(): Response<WishListResponseDto>

    @POST("v1/wishlist")
    suspend fun addToWishlist(
        @Body productId: AddToWishlistRequestDto,
    ): Response<WishListActionResponseDto>

    @DELETE("v1/wishlist/{productId}")
    suspend fun deleteWishList(@Path("productId") productId: String?): Response<WishListActionResponseDto>


}