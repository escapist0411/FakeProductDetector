package com.example.fakeproductdetector.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(

    primary = PrimaryGreen,
    onPrimary = TextPrimary,

    secondary = AccentBlue,
    onSecondary = TextPrimary,

    background = DarkBackground,
    onBackground = TextPrimary,

    surface = DarkSurface,
    onSurface = TextPrimary,

    error = RiskRed,
    onError = TextPrimary
)

@Composable
fun FakeProductDetectorTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}