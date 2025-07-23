package com.mohamed.routeshop.ui.screens.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mohamed.domain.model.categories.CategoriesList
import com.mohamed.routeshop.R
import com.mohamed.routeshop.ui.navigation.Route

@Composable
fun SubCategoryContent(
    navController: NavController,
    modifier: Modifier = Modifier,
    product: CategoriesList,

    ) {


    Column {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    navController.navigate(Route.PRODUCT_CONTENT)
                },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = product.image ?: R.drawable.ic_placeholder,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = ColorPainter(Color.Gray),
                    error = ColorPainter(Color.Red),
                    contentScale = ContentScale.Crop,
                )

            }
        }

        Text(
            text = product.name ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 2,
            color = Color.Black,
            textDecoration = TextDecoration.None,
            overflow = TextOverflow.Clip,
            modifier = Modifier.fillMaxWidth()
        )

    }


}

//@Preview(showSystemUi = true)
//@Composable
//fun SubCategoryContentPreview() {
//    val product = CategoriesList(
//        image = "https://example.com/image.jpg",
//        name = "Electronics"
//    )
//    SubCategoryContent(product)
//}

