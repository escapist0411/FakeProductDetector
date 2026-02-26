package com.example.fakeproductdetector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.fakeproductdetector.navigation.AppNavigation
import com.example.fakeproductdetector.ui.theme.FakeProductDetectorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FakeProductDetectorTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}