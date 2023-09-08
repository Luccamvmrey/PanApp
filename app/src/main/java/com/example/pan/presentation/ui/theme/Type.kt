package com.example.pan.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.pan.R

val geologica = FontFamily(
    Font(R.font.geologica_regular),
    Font(R.font.geologica_bold, weight = FontWeight.Bold),
    Font(R.font.geologica_light, weight = FontWeight.Thin),
    Font(R.font.geologica_black, weight = FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    labelSmall = TextStyle(
        fontFamily = geologica,
    ),
    labelMedium = TextStyle(
        fontFamily = geologica,
    ),
    labelLarge = TextStyle(
        fontFamily = geologica,
    ),
    bodySmall = TextStyle(
        fontFamily = geologica,
    ),
    bodyMedium = TextStyle(
        fontFamily = geologica,
    ),
    bodyLarge = TextStyle(
        fontFamily = geologica,
    ),
    titleSmall = TextStyle(
        fontFamily = geologica,
    ),
    titleMedium = TextStyle(
        fontFamily = geologica,
    ),
    titleLarge = TextStyle(
        fontFamily = geologica,
    ),
    headlineSmall = TextStyle(
        fontFamily = geologica,
    ),
    headlineMedium = TextStyle(
        fontFamily = geologica,
    ),
    headlineLarge = TextStyle(
        fontFamily = geologica,
    ),
    displaySmall = TextStyle(
        fontFamily = geologica,
    ),
    displayMedium = TextStyle(
        fontFamily = geologica,
    ),
    displayLarge = TextStyle(
        fontFamily = geologica,
    ),
)