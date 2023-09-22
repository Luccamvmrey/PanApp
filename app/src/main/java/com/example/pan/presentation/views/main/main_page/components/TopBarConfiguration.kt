package com.example.pan.presentation.views.main.main_page.components

import androidx.compose.ui.graphics.vector.ImageVector

data class TopBarConfiguration(
    val title: String,
    val actions: ImageVector,
    val onActionClick: () -> Unit
)
