package com.mohamed.routeshop.ui.screens.home.brands

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mohamed.domain.model.brands.BrandsList
import com.mohamed.routeshop.ui.viewmodel.BrandsViewModel

@Composable
fun BrandsContent(
    modifier: Modifier = Modifier,
    brandsViewModel: BrandsViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        brandsViewModel.getBrands()
    }
    LazyHorizontalGrid(
        rows = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
//        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(brandsViewModel.brandsList) { brandsList ->
            BrandsItem(brandsItem = brandsList)
        }


    }

}

@Composable
fun BrandsItem(
    modifier: Modifier = Modifier,
    brandsItem: BrandsList,
) {
    Column(
        modifier = modifier.wrapContentSize()
    ) {

        AsyncImage(
            model = brandsItem.image,
            contentDescription = "",
            modifier = modifier
                .size(100.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        )

        Text(
            text = brandsItem.name ?: "",
            modifier = modifier.align(alignment = Alignment.CenterHorizontally)
        )

    }
}