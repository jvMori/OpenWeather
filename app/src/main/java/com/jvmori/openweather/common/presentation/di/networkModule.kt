package com.jvmori.openweather.common.presentation.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideInterceptor() }
    factory { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("api.openweathermap.org/data/2.5/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient (interceptor: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .retryOnConnectionFailure(true)
        .build()
}

fun provideInterceptor(): Interceptor {
    return Interceptor { chain ->
        val url = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("appid", "77416b7095ef9d16a5f67aae805c092d")
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return@Interceptor chain.proceed(request)
    }
}