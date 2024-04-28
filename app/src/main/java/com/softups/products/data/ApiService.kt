package com.softups.products.data

import com.skydoves.sandwich.ApiResponse
import com.softups.products.data.remote.model.ProductNetwork
import com.softups.products.data.remote.model.ProductsNetwork
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/products")
    suspend fun getProducts(): ApiResponse<ProductsNetwork>
    @GET("/products/{id}")
    suspend fun getProduct(@Path("id") productId: Int): ApiResponse<ProductNetwork>
}