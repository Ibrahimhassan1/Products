package com.softups.products.data

import com.skydoves.sandwich.ApiResponse
import com.softups.products.data.remote.model.ProductNetwork
import com.softups.products.data.remote.model.ProductsNetwork

interface ProductRepository {
    suspend fun getProducts(): ApiResponse<ProductsNetwork>
    suspend fun getProduct(productId: Int): ApiResponse<ProductNetwork>
}