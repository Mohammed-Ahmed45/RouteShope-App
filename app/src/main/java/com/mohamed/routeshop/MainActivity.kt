package com.mohamed.routeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mohamed.routeshop.ui.navigation.Nav
import com.mohamed.routeshop.ui.theme.RouteShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RouteShopTheme {
                Nav()
            }
        }
    }
}

