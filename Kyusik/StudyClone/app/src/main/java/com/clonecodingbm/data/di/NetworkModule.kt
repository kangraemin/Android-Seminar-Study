package com.clonecodingbm.data.di

import com.clonecodingbm.BuildConfig
import com.clonecodingbm.data.remote.login.LoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    @Named("rams_base_url")
    fun provideBaseUrl(): String {
        return BuildConfig.RAMS_BASE_URL
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        httpLoggingInterceptor: Call.Factory,
        gsonConverterFactory: GsonConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        @Named("rams_base_url") baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .callFactory(httpLoggingInterceptor)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }
}