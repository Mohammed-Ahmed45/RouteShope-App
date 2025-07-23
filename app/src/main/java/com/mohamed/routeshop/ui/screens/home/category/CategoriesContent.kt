package com.mohamed.routeshop.ui.screens.home.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mohamed.domain.model.categories.CategoriesList
import com.mohamed.routeshop.ui.navigation.Route
import com.mohamed.routeshop.ui.viewmodel.CategoriesViewModel

@Composable
fun Categories(
    modifier: Modifier = Modifier,
    categoriesViewModel: CategoriesViewModel = hiltViewModel(),
    navController: NavController,
) {


    LaunchedEffect(Unit) {
        categoriesViewModel.getCategories()
    }

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
//        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(categoriesViewModel.categoriesList) { categoryList ->
            CategoriesContent(
                categoriesItem = categoryList,
                navController = navController
            )

        }


    }
}


@Composable
fun CategoriesContent(
    modifier: Modifier = Modifier,
    categoriesItem: CategoriesList,
    navController: NavController,
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .clip(shape = RoundedCornerShape(20.dp))
            .clickable {
                navController.navigate("${Route.PRODUCT_SCREEN}/${categoriesItem.id}")
            },
    ) {

        AsyncImage(
            model = categoriesItem.image,
            contentDescription = "",
            modifier = modifier
                .size(100.dp)
                .clip(shape = RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop

        )

        Text(
            text = categoriesItem.name ?: "",
            modifier = modifier.align(alignment = Alignment.CenterHorizontally)
        )

    }
}
