package com.softups.products.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.twotone.AccountBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.softups.products.R
import com.softups.products.presentation.product_details.ProductDetailsScreen
import com.softups.products.presentation.product_list.ProductListScreen

@Composable
fun ProductApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    var currentRoute = backStackEntry?.destination?.route ?: ProductScreen.ProductList.route
    if (currentRoute.contains("/")){
        currentRoute = currentRoute.split("/")[0]
    }
    val screenTitle =
        when (currentRoute) {
            ProductScreen.ProductList.route -> stringResource(id = R.string.product_list_title)
            ProductScreen.ProductDetails.route -> stringResource(id = R.string.product_detail_title)
            else -> stringResource(id = R.string.product_list_title)
        }

    Scaffold(
        topBar = {
            AppBar(
                screenTitle = screenTitle,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavHost(
                navController
            )
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ProductScreen.ProductList.route
    ) {
        composable(
            route = ProductScreen.ProductList.route
        ) {
            ProductListScreen(
                onProductClicked = { navController.navigate(ProductScreen.ProductDetails.route + "/${it.id}") }
            )
        }
        composable(
            route = ProductScreen.ProductDetails.route + "/{productId}"
        ) { backStackEntry ->
//            val productId = backStackEntry.arguments?.getString("productId")
//            checkNotNull(productId)
            ProductDetailsScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    screenTitle: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.TwoTone.AccountBox,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp, 48.dp)
                )
                Spacer(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium)))
                Text(
                    text = screenTitle,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}