package com.mohamed.data.interceptor

import com.mohamed.data.repositories.datasource.AuthOfflineDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AuthInterceptor @Inject constructor(
    private val offlineDataSource: AuthOfflineDataSource,
    @IODispatcher override val coroutineContext: CoroutineContext,
) : Interceptor, CoroutineScope {


    val AUTH_HEADER = "token"
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()

        val token = runBlocking {
            offlineDataSource.retrieveUser().first()?.token
        }
        token?.let { newBuilder.header(AUTH_HEADER, token) }

        return chain.proceed(newBuilder.build())
    }
}