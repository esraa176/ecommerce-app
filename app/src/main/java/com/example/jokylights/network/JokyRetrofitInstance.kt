package com.example.jokylights.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JokyRetrofitInstance {
    private const val BASE_URL = "" // TODO: Replace with real API

    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val jokyApi: JokyApiService by lazy {
        retrofit.create(JokyApiService::class.java)
    }
}