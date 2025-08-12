package com.mohamed.routeshop.ui.screens.bars

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mohamed.routeshop.ui.screens.favorite.FavoriteScreen
import com.mohamed.routeshop.ui.screens.home.HomeScreen
import com.mohamed.routeshop.ui.screens.product.ProductScreen
import com.mohamed.routeshop.ui.screens.profile.ProfileScreen
import com.mohamed.routeshop.ui.theme.Colors
import com.mohamed.routeshop.ui.utils.CustomNavItem


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navItemList = listOf(
        EnhancedNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        EnhancedNavItem("Product", Icons.Filled.List, Icons.Outlined.List),
        EnhancedNavItem("Favorite", Icons.Filled.Favorite, Icons.Outlined.Favorite),
        EnhancedNavItem("Profile", Icons.Filled.Person, Icons.Outlined.Person)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        bottomBar = {
            EnhancedBottomNavigation(
                navItemList = navItemList,
                selectedIndex = selectedIndex,
                onItemSelected = { selectedIndex = it }
            )
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = modifier.padding(innerPadding),
            selectedIndex = selectedIndex,
            navController = navController
        )
    }

}

@Composable
fun EnhancedBottomNavigation(
    navItemList: List<EnhancedNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                clip = false
            ),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            modifier = Modifier.height(100.dp),
            tonalElevation = 0.dp
        ) {
            navItemList.forEachIndexed { index, navItem ->
                val isSelected = selectedIndex == index
                val iconSize by animateDpAsState(
                    targetValue = if (isSelected) 28.dp else 24.dp,
                    animationSpec = tween(300, easing = FastOutSlowInEasing),
                    label = "iconSize"
                )

                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onItemSelected(index) },
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isSelected) Colors.DarkGreen.copy(alpha = 0.1f)
                                    else Color.Transparent
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isSelected) navItem.selectedIcon else navItem.unselectedIcon,
                                contentDescription = navItem.label,
                                modifier = Modifier.size(iconSize),
                                tint = if (isSelected) Colors.DarkGreen else Color.Gray
                            )
                        }
                    },
                    label = {
                        AnimatedVisibility(
                            visible = isSelected,
                            enter = fadeIn() + scaleIn(),
                            exit = fadeOut() + scaleOut()
                        ) {
                            Text(
                                text = navItem.label,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Colors.DarkGreen
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Colors.DarkGreen,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Colors.DarkGreen,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}

@Composable
fun CustomBottomNavigation(
    navItemList: List<EnhancedNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        shadowElevation = 15.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItemList.forEachIndexed { index, navItem ->
                CustomNavItem(
                    navItem = navItem,
                    isSelected = selectedIndex == index,
                    onClick = { onItemSelected(index) }
                )
            }
        }
    }
}


@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavController,
    categoryId: String = "",
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (selectedIndex) {
            0 -> HomeScreen(navController = navController)
            1 -> ProductScreen(navController = navController, categoryId = categoryId)
            2 -> FavoriteScreen(navController = navController)
            3 -> ProfileScreen()
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    val navController = NavHostController(LocalContext.current)
    MainScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun CustomBottomNavPreview() {
    val navItemList = listOf(
        EnhancedNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        EnhancedNavItem("Product", Icons.Filled.List, Icons.Outlined.List),
        EnhancedNavItem("Favorite", Icons.Filled.Favorite, Icons.Outlined.Favorite),
        EnhancedNavItem("Profile", Icons.Filled.Person, Icons.Outlined.Person)
    )

    CustomBottomNavigation(
        navItemList = navItemList,
        selectedIndex = 0,
        onItemSelected = {}
    )
}