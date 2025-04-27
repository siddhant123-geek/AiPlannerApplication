package com.example.aiplannerapplication.di.module

import android.content.Context
import com.example.aiplannerapplication.data.api.CustomInterceptor
import com.example.aiplannerapplication.data.api.ErrorInterceptor
import com.example.aiplannerapplication.data.api.NetworkService
import com.example.aiplannerapplication.di.ErrorInterceptorQualifier
import com.example.aiplannerapplication.di.RegularInterceptorQualifier
import com.example.aiplannerapplication.utils.DefaultNetworkHelper
import com.example.aiplannerapplication.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return "https://aiplannerbackend.onrender.com/"
    }

    @Provides
    @Singleton
    @RegularInterceptorQualifier
    fun provideInterceptor(): Interceptor {
        return CustomInterceptor()
    }

    @Provides
    @Singleton
    @ErrorInterceptorQualifier
    fun provideErrorInterceptor(): Interceptor {
        return ErrorInterceptor()
    }

    @Provides
    @Singleton
    fun provideHttpClient(@RegularInterceptorQualifier interceptor: Interceptor,
                          @ErrorInterceptorQualifier errorInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(errorInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkService(
        gsonConverterFactory: GsonConverterFactory,
        baseUrl: String,
        client: OkHttpClient
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return DefaultNetworkHelper(context)
    }
}