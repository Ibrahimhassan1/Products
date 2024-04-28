package com.softups.products.presentation.product_details

import androidx.lifecycle.SavedStateHandle
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

data class ProductState(
    val isLoading: Boolean = false,
    val isError: String = "",
    val product: Product? = null
)

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: DefaultProductRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductState())
    val uiState: StateFlow<ProductState> = _uiState

    private val productId = checkNotNull(savedStateHandle.get<String>("productId"))

    init {
        getProduct(productId.toInt())
    }

    private fun getProduct(productId: Int) {
        viewModelScope.launch {
            _uiState.value = ProductState(isLoading = true)
            when (val response = repository.getProduct(productId)) {
                is ApiResponse.Failure.Error -> _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isError = response.message()
                )

                is ApiResponse.Failure.Exception -> _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isError = response.message()
                )

                is ApiResponse.Success -> _uiState.value = ProductState(
                    isLoading = false,
                    isError = "",
                    product = response.data.toExternal()
                )
            }
        }
    }
}