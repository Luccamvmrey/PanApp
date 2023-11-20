package com.example.pan.presentation.views.main.lesson_page.components.quiz

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Answer(
    answerText: String,
    isSelected: Boolean,
    onAnswerSelected: () -> Unit
) {
    val buttonColors = if (isSelected) {
        ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    } else {
        ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        )
    }

    ElevatedButton(
        modifier = Modifier.fillMaxWidth(),
        colors = buttonColors,
        onClick = {
            onAnswerSelected()
        }
    ) {
        Text(
            text = answerText,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}