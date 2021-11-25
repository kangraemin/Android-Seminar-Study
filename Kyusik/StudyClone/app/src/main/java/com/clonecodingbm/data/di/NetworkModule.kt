package com.clonecodingbm.data.di

import com.clonecodingbm.BuildConfig
import com.clonecodingbm.data.remote.home.HomeApi
import com.clonecodingbm.data.remote.login.LoginApi
import com.clonecodingbm.data.remote.search.SearchApi
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
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    @Named("base_factory")
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
    @Named("base_retrofit")
    fun provideRetrofit(
        @Named("base_factory") httpLoggingInterceptor: Call.Factory,
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
    fun provideLoginApi(@Named("base_retrofit") retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchApi(@Named("base_retrofit") retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    ///////////////////////////////--TheMovieDB
    @Singleton
    @Provides
    @Named("tmdb_base_url")
    fun provideMovieUrl(): String {
        return BuildConfig.TMDB_BASE_URL
    }

    @Singleton
    @Provides
    @Named("tmdb_api_key")
    fun provideMovieApiKey(): String {
        return BuildConfig.TMDB_API_KEY
    }

    @Singleton
    @Provides
    fun provideTmdbApiKeyInterceptor(@Named("tmdb_api_key") apiKey: String): Interceptor {
        return Interceptor.invoke { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url

            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()
            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }

    @Singleton
    @Provides
    @Named("movie_factory")
    fun provideCallMovieFactory(
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
    @Named("movie_retrofit")
    fun provideMovieRetrofit(
        @Named("movie_factory") httpLoggingInterceptor: Call.Factory,
        gsonConverterFactory: GsonConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        @Named("tmdb_base_url") baseUrl: String
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
    fun provideHomeApi(@Named("movie_retrofit") retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }
}