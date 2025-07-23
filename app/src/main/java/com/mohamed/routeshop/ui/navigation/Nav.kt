package com.mohamed.routeshop.ui.navigation

//import com.mohamed.routeshop.ui.screens.signup.RegisterScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mohamed.routeshop.ui.MainScreen
import com.mohamed.routeshop.ui.screens.home.HomeScreen
import com.mohamed.routeshop.ui.screens.product.ProductContent
import com.mohamed.routeshop.ui.screens.product.ProductDetailsScreen
import com.mohamed.routeshop.ui.screens.product.ProductScreen
import com.mohamed.routeshop.ui.screens.signin.LogInScreen
import com.mohamed.routeshop.ui.screens.signup.RegisterScreen
import com.mohamed.routeshop.ui.screens.splash.SplashScreen

object Route {
    const val SPLASH = "splashScreen"
    const val ONBOARDING_SCREEN = "onboardingScreen"
    const val MAIN_SCREEN = "mainScreen"

    //    const val TAB_INDEX= "alerts/{tabIndex}"
    const val ALERTS = "alerts"
    const val HOME_SCREEN = "homeScreen"
    const val LOGIN_SCREEN = "LoginScreen"
    const val REGISTER_SCREEN = "RegisterScreen"

    const val FORGET_PASSWORD_SCREEN = "ForgetPasswordScreen"

    const val PRODUCT_SCREEN = "ProductScreen"
    const val PRODUCT_CONTENT = "ProductContent"

    const val FAVORITE_SCREEN = "FavoriteScreen"

    const val CART_SCREEN = "CartScreen"

    const val PROFILE_SCREEN = "ProfileScreen"
    const val PRODUCT_SCREEN_WITH_CATEGORY = "ProductScreenWithCategory"
    const val PRODUCT_DETAILS_SCREEN = "ProductDetailsScreen"

    const val PRODUCT_ID = "productId"

    const val CATEGORY_ID = "categoryId"
}


@Composable
fun Nav(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.SPLASH) {
        composable(Route.SPLASH) { SplashScreen(navController = navController) }
        composable(Route.LOGIN_SCREEN) { LogInScreen(navController = navController) }
        composable(Route.REGISTER_SCREEN) { RegisterScreen(navController = navController) }
        composable(Route.MAIN_SCREEN) { MainScreen(navController = navController) }
        composable(Route.HOME_SCREEN) { HomeScreen(navController = navController) }
        composable(Route.PRODUCT_CONTENT) { ProductContent(navController = navController) }
        composable(
            route = "${Route.PRODUCT_DETAILS_SCREEN}/{${Route.PRODUCT_ID}}",
            arguments = listOf(
                navArgument(name = Route.PRODUCT_ID) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(Route.PRODUCT_ID) ?: ""
            ProductDetailsScreen(
                productId = productId,
                navController = navController
            )
        }
//        composable(Route.SUB_CATEGORY_CONTENT) { SubCategoryContent(navController = navController) }
        composable(Route.PRODUCT_SCREEN) {
            ProductScreen(navController = navController)
        }

        composable(
            route = "${Route.PRODUCT_SCREEN}/{${Route.CATEGORY_ID}}",
            arguments = listOf(navArgument(Route.CATEGORY_ID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString(Route.CATEGORY_ID) ?: ""
            ProductScreen(
                navController = navController,
                categoryId = categoryId
            )
        }
    }
}
//@Composable
//fun Nav(modifier: Modifier = Modifier) {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = Route.SPLASH) {
//        composable(Route.SPLASH) { SplashScreen(navController = navController) }
//        composable(Route.LOGIN_SCREEN) { LogInScreen(navController = navController) }
//        composable(Route.REGISTER_SCREEN) { RegisterScreen(navController = navController) }
//        composable(Route.MAIN_SCREEN) { MainScreen(navController = navController) }
//        composable(Route.HOME_SCREEN) { HomeScreen(navController = navController) }
//        composable(
//            route = "${Route.PRODUCT_SCREEN}/${Route.CATEGORY_ID}",
//            arguments = listOf(navArgument(Route.CATEGORY_ID) {
//                type = NavType.StringType
//            })
//
//        ) { backStackEntry ->
//            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
//            ProductScreen(
//                navController = navController,
//                categoryId = categoryId
//            )
//        }
//    }
//}
