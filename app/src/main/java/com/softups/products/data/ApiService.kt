package com.softups.products.data

import com.skydoves.sandwich.ApiResponse
import com.softups.products.data.remote.model.ProductsNetwork
import retrofit2.http.GET

interface ApiService {
    @GET("/products")
    suspend fun getProducts(): ApiResponse<ProductsNetwork>
}