package com.example.pan.presentation.views.main.lesson_page.components.quiz

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScoreboardItem(
    success: Boolean,
    enabled: Boolean,
) {
    val borderColor = if (!enabled) {
        MaterialTheme.colorScheme.onSurface
    } else if (success) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.error
    }

    val icon = if (success) {
        Icons.Filled.Check
    } else {
        Icons.Filled.Close
    }

    val contentDescription = if (success) {
        "Resposta correta"
    } else {
        "Resposta incorreta"
    }

    val tint = if (success) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.error
    }

    Box(
        modifier = Modifier
            .size(32.dp)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = CircleShape
            )
            .padding(4.dp),
    ) {
        if (enabled) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = tint
            )
        }
    }
}