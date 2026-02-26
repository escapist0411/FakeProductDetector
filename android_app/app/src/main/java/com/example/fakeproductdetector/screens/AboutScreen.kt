package com.example.fakeproductdetector.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AboutScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "About Us",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "Fake Product Detector uses AI to identify counterfeit products instantly."
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Follow Us")

        Text("Instagram: @fakeproductdetector")
        Text("LinkedIn: fake-product-detector")
        Text("Website: www.fakeproductdetector.com")

        Spacer(modifier = Modifier.height(30.dp))

        Text("© 2026 All Rights Reserved")
    }
}