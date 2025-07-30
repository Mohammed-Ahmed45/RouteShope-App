package com.mohamed.routeshop.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mohamed.domain.model.product.ProductList
import com.mohamed.routeshop.R
import com.mohamed.routeshop.ui.viewmodel.WishListViewModel

@Composable
fun WishlistItemCard(
    modifier: Modifier = Modifier,
    product: ProductList,
    wishListViewModel: WishListViewModel = hiltViewModel(),
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(398.dp, 113.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = product.imageCover ?: R.drawable.img_product,
                contentDescription = "",
                modifier = modifier.size(120.dp, 113.dp),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.img_product),
                placeholder = painterResource(R.drawable.img_product)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.width(160.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.title ?: "Unknown ProductCartItemDto",
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    lineHeight = 2.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.padding(bottom = 20.dp)
                )
                Text(
                    text = if (product.priceAfterDiscount != null) {

                        "Price: EGP ${product.priceAfterDiscount}"
                    } else {
                        "Price: EGP ${product.price}"
                    },
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = {
                        wishListViewModel.deleteFromWishList(product.id!!)
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier = modifier
                        .background(Color.Transparent)
                        .align(alignment = Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        tint = Color(0xFF007BFF),
                        modifier = Modifier.size(24.dp)
                    )
                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Prev() {

    WishlistItemCard(
        product = ProductList(
            id = "1",
            title = "Sample ProductCartItemDto",
            description = "This is a sample product description.",
            price = 100,
            priceAfterDiscount = 80,
            imageCover = "https://via.placeholder.com/150",
            images = listOf("https://via.placeholder.com/150", "https://via.placeholder.com/150"),
            ratingsAverage = 4.5,
            ratingsQuantity = 120
        ),
    )
}