package com.example.jokylights.network

import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JokyRetrofitInstance {
    private const val BASE_URL = "https://sosojoky.nilecoding.com/wp-json/wc/v3/"

    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val credentials = Credentials.basic("", "") // remove this entirely and make it more secure
            val request = chain.request().newBuilder()
                .addHeader("Authorization", credentials)
                .build()
            chain.proceed(request)
        }
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