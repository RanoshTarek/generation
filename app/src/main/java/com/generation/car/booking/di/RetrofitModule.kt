package com.generation.car.booking.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.generation.car.booking.network.RestService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import org.koin.dsl.module


const val Generation_base_url = "https://generation.com/"

val networkModule = module {
    factory { getHttpLoggingInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideRetailerApi(get()) }
    factory { provideRetrofit(get(), Generation_base_url) }
}

fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    val gsonBuilder = GsonBuilder()
        .setLenient()
        .create()
    return Retrofit.Builder().baseUrl(url).client(okHttpClient)
        .addConverterFactory(
            GsonConverterFactory.create(gsonBuilder)
        )
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}


fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(MockInterceptor())
    return okHttpClientBuilder.build()
}


fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
    return httpLoggingInterceptor
}

fun provideRetailerApi(retrofit: Retrofit): RestService = retrofit.create(
    RestService::class.java
)
