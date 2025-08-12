@file:OptIn(ExperimentalMaterial3Api::class)

package com.mohamed.routeshop.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mohamed.domain.model.categories.CategoriesList
import com.mohamed.routeshop.ui.viewmodel.CategoriesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    categoriesViewModel: CategoriesViewModel = hiltViewModel(),
    navController: NavController,
    categoryList: CategoriesList? = null,
    categoryId: String = "",
) {
    var selectedCategory by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        categoriesViewModel.getCategories()
    }

    LaunchedEffect(categoryId) {
        if (categoryId.isNotEmpty()) {
            selectedCategory = categoryId
        } else if (selectedCategory.isEmpty() && categoriesViewModel.categoriesList.isNotEmpty()) {
            selectedCategory = categoriesViewModel.categoriesList.first().id ?: ""
        }
    }
    LaunchedEffect(selectedCategory) {
        categoriesViewModel.getSubCategory(categoryId = selectedCategory)
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        NavigationRail(
            modifier = Modifier
                .fillMaxHeight()
                .width(180.dp),
            containerColor = Color.White,
            contentColor = Color.Black
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Categories",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categoriesViewModel.categoriesList) { category ->
                        CategoryItem(
                            category = category,
                            isSelected = selectedCategory == category.id,
                            onClick = {
                                selectedCategory = category.id ?: ""
                            }
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(categoriesViewModel.subCategoriesList) { product ->
                    SubCategoryContent(
                        product = product,
                        navController = navController
                    )
                }
            }
        }
    }
}

