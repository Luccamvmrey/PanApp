package com.example.pan.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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
        fontSize = 11.sp,
        lineHeight = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = geologica,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = geologica,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = geologica,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = geologica,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = geologica,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = geologica,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = geologica,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = geologica,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = geologica,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = geologica,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = geologica,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    displaySmall = TextStyle(
        fontFamily = geologica,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),
    displayMedium = TextStyle(
        fontFamily = geologica,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),
    displayLarge = TextStyle(
        fontFamily = geologica,
        fontSize = 57.sp,
        lineHeight = 64.sp
    ),
)