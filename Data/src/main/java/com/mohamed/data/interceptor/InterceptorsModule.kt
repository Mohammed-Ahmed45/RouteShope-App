package com.mohamed.data.interceptor

import android.content.Context
import com.mohamed.data.appnetetwork.AppNetworkHandler
import com.mohamed.data.repositories.datasource.AuthOfflineDataSource

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.Interceptor
import java.io.File
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object InterceptorsModule {

    @Provides
    @Singleton
    @OkHttpAuthInterceptor
    fun provideAuthInterceptor(
        offlineDataSource: AuthOfflineDataSource,
        @IODispatcher ioDispatcher: CoroutineContext,
    ): Interceptor {
        return AuthInterceptor(
            offlineDataSource,
            ioDispatcher
        )
    }


    @Provides
    @Singleton
    @OkHttpOfflineCacheInterceptor
    fun provideOfflineCacheInterceptor(networkHandler: AppNetworkHandler): Interceptor {
        return OfflineCacheInterceptor(networkHandler)
    }

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        return Cache(
            File(context.cacheDir, "news-cache"),
            10L * 1024L * 1024L
        ) // 10 MiB

    }

    @Provides
    @Singleton
    @SmartCashInterceptor
    fun provideSmartCashInterceptor(): Interceptor {
        return SmartCacheInterceptor()
    }

    @Provides
    @Singleton
    @IODispatcher
    fun provideIODispatcher(): CoroutineContext = Dispatchers.IO

    @Provides
    @Singleton
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineContext = Dispatchers.Main
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpAuthInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpOfflineCacheInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SmartCashInterceptor