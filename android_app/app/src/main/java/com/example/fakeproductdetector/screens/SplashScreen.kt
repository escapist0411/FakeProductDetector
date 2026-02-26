package com.example.fakeproductdetector.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fakeproductdetector.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember {
        Animatable(0.5f)
    }

    LaunchedEffect(true) {

        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1200,
                easing = FastOutSlowInEasing
            )
        )

        delay(1500)

        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.app_logo),
                contentDescription = null,
                modifier = Modifier.scale(scale.value)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Fake Product Detector",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}