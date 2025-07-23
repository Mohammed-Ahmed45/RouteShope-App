package com.mohamed.routeshop.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mohamed.routeshop.R
import com.mohamed.routeshop.ui.screens.home.brands.BrandsContent
import com.mohamed.routeshop.ui.screens.home.category.Categories
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

            Image(
                painter = painterResource(id = R.drawable.ic_cart),
                contentDescription = "",
                modifier = modifier
                    .size(28.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
        }
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


//@Preview(showSystemUi = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen()
//}


