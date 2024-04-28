package com.softups.products.presentation

import androidx.annotation.StringRes
import com.softups.products.R

enum class ProductScreen(@StringRes val title: Int, val route: String) {
    ProductList(title = R.string.product_list_title, route = "product_list"),
    ProductDetails(title = R.string.product_detail_title, route = "product_detail/{productId}")
}