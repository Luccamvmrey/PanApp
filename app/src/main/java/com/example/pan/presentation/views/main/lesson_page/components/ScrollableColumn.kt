package com.example.pan.presentation.views.main.lesson_page.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScrollableColumn(
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        Modifier.verticalScroll(scrollState)
    ) {
        content()
    }
}