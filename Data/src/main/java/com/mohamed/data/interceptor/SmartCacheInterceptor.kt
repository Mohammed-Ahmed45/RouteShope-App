package com.mohamed.data.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class SmartCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val cacheControl = when {
            request.url.encodedPath.contains("/cart") -> {
                CacheControl.Builder()
                    .noCache()
                    .noStore()
                    .build()
            }

            request.url.encodedPath.contains("/wishlist") -> {
                CacheControl.Builder()
                    .noCache()
                    .noStore()
                    .build()
            }

            request.url.encodedPath.contains("/products") -> {
                CacheControl.Builder()
                    .maxAge(5, TimeUnit.DAYS)
                    .build()
            }

            request.url.encodedPath.contains("/categories") ||
                    request.url.encodedPath.contains("/brands") -> {
                CacheControl.Builder()
                    .maxAge(1, TimeUnit.DAYS)
                    .build()
            }

            else -> {
                CacheControl.Builder()
                    .maxAge(30, TimeUnit.MINUTES)
                    .build()
            }
        }

        return response.newBuilder()
            .removeHeader("Pragma")
            .removeHeader("Cache-Control")
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}