package com.mohamed.data.api

import com.mohamed.data.api.model.request.SignInRequestDto
import com.mohamed.data.api.model.request.SignUpRequestDto
import com.mohamed.data.api.model.response.auth.AuthResponseDto
import com.mohamed.data.api.model.response.brands.BrandsResponseDto
import com.mohamed.data.api.model.response.categories.CategoriesResponseDto
import com.mohamed.data.api.model.response.product.ProductResponseDto
import com.mohamed.data.api.model.response.productdetails.ProductDetailsResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
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


//    @POST("v1/auth/signup")
//    suspend fun signUp(@Body request: SignUpRequestDto): Response<AuthResponseDto>


}