package com.softups.products.data

import com.skydoves.sandwich.ApiResponse
import com.softups.products.data.remote.model.ProductsNetwork
import javax.inject.Inject

class DefaultProductRepository @Inject constructor(private val apiService: ApiService) : ProductRepository {
    override suspend fun getProducts(): ApiResponse<ProductsNetwork> {
        return apiService.getProducts()
    }
}