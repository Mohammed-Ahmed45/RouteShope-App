@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.mohamed.routeshop.ui.screens.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mohamed.routeshop.ui.navigation.Route
import com.mohamed.routeshop.ui.theme.Colors
import com.mohamed.routeshop.ui.utils.ProductCard
import com.mohamed.routeshop.ui.utils.SearchBar
import com.mohamed.routeshop.ui.viewmodel.ProductViewModel

@Composable
fun ProductContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    productViewModel: ProductViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        productViewModel.getProduct()
    }
    var searchQuery by remember { mutableStateOf("") }

    val filteredProduct = if (searchQuery.isEmpty()) {
        productViewModel.productList
    } else {
        productViewModel.productList.filter { productList ->
            productList.title?.contains(searchQuery, ignoreCase = true) == true
                    || productList.description?.contains(searchQuery, ignoreCase = true) == true
        }
    }
    Column(modifier = modifier) {
        Row(
            modifier = modifier.padding(top = 16.dp),
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChange = { newProduct ->
                    searchQuery = newProduct
                }
            )
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Rating",
                tint = Colors.DarkGreen,
                modifier = modifier
                    .size(32.dp)
                    .clickable {
                        navController.navigate(Route.CART_SCREEN)
                    }
                    .size(28.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()

        ) {
            items(filteredProduct) { productItems ->
                ProductCard(
                    productItems = productItems,
                    navController = navController
                )
            }
        }


    }
}