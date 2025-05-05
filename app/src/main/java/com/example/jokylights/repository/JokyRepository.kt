package com.example.jokylights.repository

import com.example.jokylights.network.JokyApiService

class JokyRepository(private val jokyApi: JokyApiService) {
    suspend fun getProducts() = jokyApi.getProducts()
}