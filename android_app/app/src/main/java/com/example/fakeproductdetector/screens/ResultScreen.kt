package com.example.fakeproductdetector.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import java.io.File

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ResultScreen(
    navController: NavController,
    prediction: String,
    confidence: String,
    imagePath: String
) {

    val confidenceValue = confidence.toFloatOrNull() ?: 0f

    val (riskText, riskColor) = when {
        confidenceValue >= 80 -> "SAFE" to Color(0xFF2E7D32)
        confidenceValue >= 50 -> "SUSPICIOUS" to Color(0xFFFFA000)
        else -> "HIGH RISK" to Color(0xFFC62828)
    }

    val animatedProgress by animateFloatAsState(
        targetValue = confidenceValue / 100f,
        animationSpec = tween(1500),
        label = ""
    )

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF121212),
            Color(0xFF1E1E1E)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        // Image
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(1000)) + scaleIn()
        ) {
            androidx.compose.foundation.Image(
                painter = rememberAsyncImagePainter(File(imagePath)),
                contentDescription = null,
                modifier = Modifier
                    .size(220.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2A2A2A)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = prediction,
                    fontSize = 30.sp,
                    color = if (prediction == "REAL")
                        Color(0xFF4CAF50)
                    else Color(0xFFF44336)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = riskText,
                    fontSize = 18.sp,
                    color = riskColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                LinearProgressIndicator(
                    progress = animatedProgress,
                    modifier = Modifier.fillMaxWidth(),
                    color = riskColor,
                    trackColor = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$confidence %",
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50)
            )
        ) {
            Text("Back to Home")
        }
    }
}