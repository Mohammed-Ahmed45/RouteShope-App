package com.mohamed.data.interceptor


import android.Manifest
import androidx.annotation.RequiresPermission
import com.mohamed.data.appnetetwork.AppNetworkHandler
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class OfflineCacheInterceptor @Inject constructor(private val networkHandler: AppNetworkHandler) :
    Interceptor {
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val maxStale = 60 * 60 * 24 * 10

        if (!networkHandler.isNetworkAvailable()) {
            builder
                .removeHeader("Pragma")
                .header(
                    "Cache-Control",
                    "public, only-if-cached=$maxStale"
                ) // The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.

        }
        return chain.proceed(builder.build());
    }
}