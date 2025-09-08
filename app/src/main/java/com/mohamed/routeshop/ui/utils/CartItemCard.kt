package com.mohamed.routeshop.ui.utils


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import coil.compose.AsyncImage
import com.mohamed.domain.model.cart.ProductsListItem
import com.mohamed.routeshop.R
import com.mohamed.routeshop.ui.theme.Colors
import com.mohamed.routeshop.ui.viewmodel.CartViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun CartItemsCard(
    modifier: Modifier = Modifier,
    product: ProductsListItem,
    cartViewModel: CartViewModel = hiltViewModel(),
) {
    val swipeState = rememberSwipeableState(initialValue = 0)
    val density = LocalDensity.current
    val swipeThreshold = with(density) { 300.dp.toPx() }
    var isVisible by remember { mutableStateOf(true) }
    var isRemoving by remember { mutableStateOf(false) }


    fun deleteWithAnimation() {
        if (!isRemoving) {
            isRemoving = true
        }
    }

    LaunchedEffect(swipeState.targetValue) {
        if (swipeState.targetValue == 1) {
            deleteWithAnimation()
        }
    }


    LaunchedEffect(isRemoving) {
        if (isRemoving) {

            cartViewModel.deleteFromCart(product.product?.id!!)
            if (cartViewModel.errorMessage == null) {
                isVisible = false
            } else {
                isRemoving = false
            }
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        exit = fadeOut(
            animationSpec = tween(durationMillis = 300)
        )
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(113.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red, RoundedCornerShape(8.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Delete",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(398.dp, 113.dp)
                    .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) }
                    .swipeable(
                        state = swipeState,
                        anchors = mapOf(
                            0f to 0,
                            -swipeThreshold to 1
                        ),
                        thresholds = { _, _ -> FractionalThreshold(0.3f) },
                        orientation = Orientation.Horizontal
                    ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    AsyncImage(
                        model = product.product?.imageCover ?: R.drawable.img_product,
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
                            text = product.product?.title ?: "Unknown ProductCartItemDto",
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            lineHeight = 2.sp,
                            overflow = TextOverflow.Ellipsis,
                            modifier = modifier.padding(bottom = 20.dp)
                        )
                        Text(
                            text = "EGP ${product.price.toString()}",
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
                                cartViewModel.deleteFromCart(product.product?.id!!)
                            },
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            modifier = modifier
                                .background(Color.Transparent)
                                .align(alignment = Alignment.TopEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "",
                                tint = Colors.DarkGreen,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                    }
                }
            }
        }
    }
}