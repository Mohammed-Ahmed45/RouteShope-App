@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.mohamed.routeshop.ui.screens.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mohamed.routeshop.R
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

    Column(modifier = modifier) {
        Row(
            modifier = modifier.padding(top = 16.dp),
        ) {
            SearchBar(
                query = "",
                onQueryChange = {}
            )
            Image(
                painter = painterResource(id = R.drawable.ic_cart),
                contentDescription = "",
                modifier = modifier
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
            items(productViewModel.productList) { productItems ->
                ProductCard(
                    productItems = productItems,
                    navController = navController
                )
            }
        }


    }
}


