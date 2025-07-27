package com.mohamed.routeshop.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mohamed.domain.model.product.ProductList
import com.mohamed.routeshop.R
import com.mohamed.routeshop.ui.navigation.Route.PRODUCT_DETAILS_SCREEN
import com.mohamed.routeshop.ui.theme.colors
import com.mohamed.routeshop.ui.viewmodel.WishListViewModel

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    navController: NavController,
    productItems: ProductList,
    wishListViewModel: WishListViewModel = hiltViewModel(),

    ) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .height(300.dp)
            .fillMaxWidth()
            .clickable {

                navController.navigate("${PRODUCT_DETAILS_SCREEN}/${productItems.id}")
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Product Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                val imageUrl = productItems.images?.firstOrNull { !it.isNullOrBlank() }
                    ?: productItems.imageCover

                if (!imageUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = productItems.title,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                        contentScale = ContentScale.Crop,
                        error = painterResource(R.drawable.img_product),
                        placeholder = painterResource(R.drawable.img_product)
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.img_product),
                        contentDescription = productItems.title,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                // Discount Badge
                if (productItems.priceAfterDiscount != null &&
                    productItems.price?.let { productItems.priceAfterDiscount!! < it } == true
                ) {
                    val discountPercentage =
                        ((productItems.price!! - productItems.priceAfterDiscount!!) * 100 / productItems.price!!).toInt()
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(
                                color = Color.Red,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "-$discountPercentage%",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(12.dp)
            ) {
                Text(
                    text = productItems.title ?: "Product Name",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = colors.LightBlue,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = productItems.description ?: "Product Description",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        if (productItems.priceAfterDiscount != null &&
                            productItems.price?.let { productItems.priceAfterDiscount!! < it } == true
                        ) {
                            Text(
                                text = "${productItems.price} EGP",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                textDecoration = TextDecoration.LineThrough
                            )
                            Text(
                                text = "${productItems.priceAfterDiscount} EGP",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = colors.LightBlue
                            )
                        } else {
                            Text(
                                text = "${productItems.price} EGP",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = colors.LightBlue
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                color = colors.LightBlue,
                                shape = CircleShape
                            ), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_to_cart),
                            contentDescription = "Add to Cart",
                            tint = Color.White,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    wishListViewModel.addToWishList(productItems.id!!)
                                }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFB800),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${productItems.ratingsAverage ?: 0.0}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }

                    Text(
                        text = "${productItems.ratingsQuantity ?: 0} reviews",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductCardPreview() {
    val navController = rememberNavController()
    val productItems = ProductList(
        id = "1",
        title = "Sample Product",
        description = "This is a sample product description.",
        price = 100,
        priceAfterDiscount = 80,
        imageCover = "https://via.placeholder.com/150",
        images = listOf("https://via.placeholder.com/150", "https://via.placeholder.com/150"),
        ratingsAverage = 4.5,
        ratingsQuantity = 120
    )
    ProductCard(
        navController = navController,
        productItems = productItems,
    )
}


