package com.mohamed.routeshop.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mohamed.routeshop.ui.screens.favorite.FavoriteScreen
import com.mohamed.routeshop.ui.screens.home.HomeScreen
import com.mohamed.routeshop.ui.screens.product.ProductScreen
import com.mohamed.routeshop.ui.screens.profile.ProfileScreen
import com.mohamed.routeshop.ui.theme.colors
import com.mohamed.routeshop.ui.utils.NavItem


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navItemList = listOf(
        NavItem("Home", icon = Icons.Default.Home),
        NavItem("Product", icon = Icons.Default.List),
        NavItem("Favorite", icon = Icons.Default.Favorite),
        NavItem("Profile", icon = Icons.Default.Person)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(

        modifier = modifier
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .fillMaxSize(),
        bottomBar = {

            Surface(
                shape = RoundedCornerShape(topEnd = 14.dp, topStart = 14.dp)
            ) {
                NavigationBar(
                    containerColor = colors.DarkGreen,
                ) {
                    navItemList.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                            },
                            icon = {
                                Icon(imageVector = navItem.icon, contentDescription = "")
                            },
                            label = { Text(text = navItem.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                unselectedIconColor = Color.Black,
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color.Gray,
                                indicatorColor = Color.Transparent,
                            )

                        )
                    }
                }

            }
        }
    )
    { innerPadding ->
        ContentScreen(
            modifier = modifier
                .padding(innerPadding),
            selectedIndex = selectedIndex,
            navController = navController
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    val navController = NavHostController(androidx.compose.ui.platform.LocalContext.current)
    MainScreen(navController = navController)
}


@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavController,
    categoryId: String = "",
) {
    when (selectedIndex) {
        0 -> HomeScreen(navController = navController)
        1 -> ProductScreen(navController = navController, categoryId = categoryId)
        2 -> FavoriteScreen(navController = navController)
        3 -> ProfileScreen()
    }

}








