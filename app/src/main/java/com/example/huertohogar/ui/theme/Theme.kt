package com.example.huertohogar.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Paleta de colores para el tema claro (existente)
private val LightColorScheme = lightColorScheme(
    primary = PrimaryGreen,
    secondary = SearchBarGreen,
    background = BackgroundCream,
    surface = CardBackground,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
)

// --- AÑADIDO: Paleta de colores para el tema oscuro ---
private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = SearchBarGreen, // Reutilizamos el verde de búsqueda
    background = DarkBackground,
    surface = DarkCardBackground,
    onPrimary = TextPrimary, // Texto oscuro sobre el primario claro
    onSecondary = Color.White, // Texto blanco sobre el secundario
    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary,
)

@Composable
fun HuertoHogarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // --- CORREGIDO: Se elige el esquema de color dinámicamente ---
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            // --- CORREGIDO: La apariencia de los iconos de la barra de estado es la opuesta al tema ---
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}