package com.softups.products.data.remote.model

import com.softups.products.presentation.model.Product

data class ProductsNetwork(
    val products: List<ProductNetwork>
)
data class ProductNetwork(
    val id: Int,
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
)

fun ProductNetwork.toExternal() = Product(
    id = id,
    brand = brand,
    category = category,
    description = description,
    discountPercentage = discountPercentage,
    images = images,
    price = price,
    rating = rating,
    stock = stock,
    thumbnail = thumbnail,
    title = title
)