package com.clonecodingbm.network

import com.clonecodingbm.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL =
        "https://r5670326j8.execute-api.ap-northeast-2.amazonaws.com/delivery_server/"

    @Singleton
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideCallFactory(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        tmdbApiKeyInterceptor: Interceptor
    ): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(tmdbApiKeyInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String {
        return BuildConfig.RAMS_BASE_URL
    }

    @Singleton
    @Provides
    @Named("rams_api_key")
    fun provideRamsApiKey(): String {
        return BuildConfig.RAMS_API_KEY
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        httpLoggingInterceptor: Call.Factory,
        moshiConverterFactory: MoshiConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        @Named("rams_base_url") baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .callFactory(httpLoggingInterceptor)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }
}