package com.example.jokylights.network

import com.example.jokylights.model.Products
import retrofit2.http.GET

interface JokyApiService {
    @GET("/products")
    suspend fun getProducts(): Products
}