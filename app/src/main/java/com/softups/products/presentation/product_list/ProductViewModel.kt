package com.softups.products.presentation.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.softups.products.data.DefaultProductRepository
import com.softups.products.data.remote.model.toExternal
import com.softups.products.presentation.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductListState(
    val isLoading: Boolean = false,
    val isError: String = "",
    val productList: List<Product> = emptyList()
)

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: DefaultProductRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(ProductListState())
    val uiState: StateFlow<ProductListState> = _uiState

    init {
        refreshProducts()
    }

    private fun refreshProducts() {
        viewModelScope.launch {
            val response = productRepository.getProducts()

            _uiState.value = ProductListState(isLoading = true)
            when (response) {
                is ApiResponse.Failure.Error -> _uiState.value =
                    _uiState.value.copy(isLoading = false, isError = response.message())

                is ApiResponse.Failure.Exception -> _uiState.value =
                    _uiState.value.copy(isLoading = false, isError = response.message())

                is ApiResponse.Success -> _uiState.value = ProductListState(
                    isLoading = false,
                    isError = "",
                    productList = response.data.products.map { it.toExternal() })
            }
        }
    }
}