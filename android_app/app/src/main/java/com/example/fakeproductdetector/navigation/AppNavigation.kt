package com.example.fakeproductdetector.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.fakeproductdetector.screens.*

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }

        composable("signup") {
            SignupScreen(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("scan") {
            ScanScreen(navController)
        }
        composable("history") { HistoryScreen(navController) }
        composable("about") {
            AboutScreen()
        }

        composable("contact") {
            ContactScreen()
        }
        composable(
            "result/{prediction}/{confidence}/{imageUri}"
        ) { backStackEntry ->

            val prediction =
                backStackEntry.arguments?.getString("prediction") ?: "Unknown"

            val confidence =
                backStackEntry.arguments?.getString("confidence") ?: "0"

            val encodedPath =
                backStackEntry.arguments?.getString("imageUri") ?: ""

            val imagePath =
                java.net.URLDecoder.decode(encodedPath, "UTF-8")

            ResultScreen(
                navController,
                prediction,
                confidence,
                imagePath
            )
        }
    }
}