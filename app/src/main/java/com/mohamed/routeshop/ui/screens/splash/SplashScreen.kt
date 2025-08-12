package com.mohamed.routeshop.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mohamed.routeshop.R
import com.mohamed.routeshop.ui.navigation.Route
import com.mohamed.routeshop.ui.theme.Colors
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier = Modifier, navController: NavController? = null) {
    //zoom
    val logoScale = remember { Animatable(0f) }
    // hide / show
    val logoAlpha = remember { Animatable(0f) }
    // degree
    val logoRotate = remember { Animatable(0f) }

    val texScale = remember { Animatable(0f) }
    val textAlpha = remember { Animatable(0f) }

    val textOffsetY = remember { Animatable(100f) }


    LaunchedEffect(Unit) {

        logoAlpha.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        logoRotate.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        logoScale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        delay(1000)

        textAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 400),
        )

        texScale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )
        )

        delay(1000)

        //hide logo and text
        logoAlpha.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 400)
        )
        textAlpha.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 400)
        )

        navController?.navigate(Route.LOGIN_SCREEN) {
            popUpTo(Route.SPLASH) {
                inclusive = true
            }
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Colors.DarkGreen),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_app),
            contentDescription = "",
            modifier = modifier
                .size(283.dp, 130.dp)
                .scale(logoScale.value)
                .alpha(logoAlpha.value)


        )
        Image(
            painter = painterResource(id = R.drawable.ic_text_app),
            contentDescription = "",
            modifier = modifier
                .size(283.dp, 130.dp)
                .scale(texScale.value)
                .alpha(textAlpha.value)


        )

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    SplashScreen()
}