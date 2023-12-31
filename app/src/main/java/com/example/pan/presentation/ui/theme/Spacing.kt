package com.example.pan.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    /**
     * The default spacing used throughout.
     * default = 0.dp
     * extraSmall = 8.dp
     * small = 12.dp
     * smallMedium = 24.dp
     * medium = 32.dp
     * large = 48.dp
     * extraLarge = 64.dp
     */
    val default: Dp = 0.dp,
    val extraSmall: Dp = 8.dp,
    val small: Dp = 12.dp,
    val smallMedium: Dp = 24.dp,
    val medium: Dp = 32.dp,
    val large: Dp = 48.dp,
    val extraLarge: Dp = 64.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current