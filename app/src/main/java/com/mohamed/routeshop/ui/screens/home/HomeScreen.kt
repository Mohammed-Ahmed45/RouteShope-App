package com.mohamed.routeshop.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mohamed.routeshop.ui.navigation.Route
import com.mohamed.routeshop.ui.screens.home.brands.BrandsContent
import com.mohamed.routeshop.ui.screens.home.category.Categories
import com.mohamed.routeshop.ui.theme.Colors
import com.mohamed.routeshop.ui.utils.FeaturedBanner
import com.mohamed.routeshop.ui.utils.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row {
            SearchBar(query = "", onQueryChange = {})
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Rating",
                tint = Colors.DarkGreen,
                modifier = modifier
                    .size(32.dp)
                    .clickable {
                        navController.navigate(Route.CART_SCREEN)
                    }
                    .align(alignment = Alignment.CenterVertically)
            )
        }

        LazyColumn {
            items(1) {
                FeaturedBanner()

                //Brands
                BrandsContent()
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Categories", fontSize = 18.sp)
                    Text(text = "view all", fontSize = 12.sp)
                }

                // category
                Categories(navController = navController)
            }
        }


    }

}


@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}

