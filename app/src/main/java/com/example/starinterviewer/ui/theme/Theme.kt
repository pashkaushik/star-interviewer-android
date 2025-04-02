package com.example.starinterviewer.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = AirbnbAccentRed,
    secondary = AirbnbAccentGreen,
    tertiary = AirbnbAccentBlue,
    surface = AirbnbNeutral900,
    background = AirbnbNeutral800,
    onPrimary = AirbnbWhite,
    onSecondary = AirbnbWhite,
    onTertiary = AirbnbWhite,
    onSurface = AirbnbWhite,
    onBackground = AirbnbWhite,
    error = AirbnbError,
    onError = AirbnbWhite,
    surfaceVariant = AirbnbNeutral700,
    onSurfaceVariant = AirbnbNeutral200,
    inverseSurface = AirbnbNeutral100,
    inverseOnSurface = AirbnbNeutral900,
    outline = AirbnbNeutral600,
    outlineVariant = AirbnbNeutral700,
    scrim = AirbnbNeutral900.copy(alpha = 0.5f)
)

private val LightColorScheme = lightColorScheme(
    primary = AirbnbAccentRed,
    secondary = AirbnbAccentGreen,
    tertiary = AirbnbAccentBlue,
    surface = AirbnbWhite,
    background = AirbnbLightGray,
    onPrimary = AirbnbWhite,
    onSecondary = AirbnbBlack,
    onTertiary = AirbnbWhite,
    onSurface = AirbnbBlack,
    onBackground = AirbnbBlack,
    error = AirbnbError,
    onError = AirbnbWhite,
    surfaceVariant = AirbnbNeutral100,
    onSurfaceVariant = AirbnbNeutral700,
    inverseSurface = AirbnbNeutral900,
    inverseOnSurface = AirbnbNeutral100,
    outline = AirbnbNeutral300,
    outlineVariant = AirbnbNeutral200,
    scrim = AirbnbNeutral900.copy(alpha = 0.5f)
)

@Composable
fun StarInterviewerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}