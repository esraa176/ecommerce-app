package com.example.jokylights.network

import com.example.jokylights.model.Products
import retrofit2.http.GET
import retrofit2.http.Query

interface JokyApiService {
    @GET("products")
    suspend fun getProducts(@Query("per_page") perPage: Int = 50): Products
}