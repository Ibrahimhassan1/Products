package com.softups.products.presentation.product_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.softups.products.R
import com.softups.products.presentation.model.Product

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel(),
    onProductClicked: (Product) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductListScreen(uiState, onProductClicked = onProductClicked)
}

@Composable
fun ProductListScreen(uiState: ProductListState, onProductClicked: (Product) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else if (uiState.isError.isNotEmpty()) {
            Text(text = uiState.isError)
        } else if (uiState.productList.isEmpty()) {
            Text(text = "Empty.")
        } else {
            ProductList(uiState.productList, onProductClicked = onProductClicked)
        }
    }
}

@Composable
fun ProductList(productList: List<Product>, onProductClicked: (Product) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
    ) {
        items(productList) {
            ProductItem(product = it, onProductClicked = onProductClicked)
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    onProductClicked: (Product) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onProductClicked(product) })
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        ) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(180.dp)
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
            Text(text = product.title, style = MaterialTheme.typography.headlineMedium)
            Text(text = "$${product.price}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}