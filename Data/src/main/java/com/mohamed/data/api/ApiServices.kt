package com.mohamed.data.api

import com.mohamed.data.api.model.request.ProductRequestDto
import com.mohamed.data.api.model.request.SignInRequestDto
import com.mohamed.data.api.model.request.SignUpRequestDto
import com.mohamed.data.api.model.response.auth.AuthResponseDto
import com.mohamed.data.api.model.response.brands.BrandsResponseDto
import com.mohamed.data.api.model.response.cart.addtocart.ActionCartResponseDto
import com.mohamed.data.api.model.response.cart.getcart.CartResponseDto
import com.mohamed.data.api.model.response.categories.CategoriesResponseDto
import com.mohamed.data.api.model.response.product.ProductResponseDto
import com.mohamed.data.api.model.response.productdetails.ProductDetailsResponseDto
import com.mohamed.data.api.model.response.wishlist.ActionResponseDto
import com.mohamed.data.api.model.response.wishlist.WishListResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {
    
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


    // DataCart
    @Headers("Cache-Control: no-cache")
    @GET("v1/cart")
    suspend fun getCartList(): Response<CartResponseDto>

    @Headers("Cache-Control: no-cache")
    @POST("v1/cart")
    suspend fun addToCart(
        @Body productId: ProductRequestDto,
    ): Response<ActionCartResponseDto>

    @Headers("Cache-Control: no-cache")
    @DELETE("v1/cart/{productId}")
    suspend fun deleteFromCart(
        @Path("productId") productId: String?,
    ): Response<ActionCartResponseDto>


    //wishList
    @Headers("Cache-Control: no-cache")
    @GET("v1/wishlist")
    suspend fun getWishList(): Response<WishListResponseDto>


    @POST("v1/wishlist")
    suspend fun addToWishlist(
        @Body productId: ProductRequestDto,
    ): Response<ActionResponseDto>

    @Headers("Cache-Control: no-cache")
    @DELETE("v1/wishlist/{productId}")
    suspend fun deleteWishList(@Path("productId") productId: String?): Response<ActionResponseDto>


}