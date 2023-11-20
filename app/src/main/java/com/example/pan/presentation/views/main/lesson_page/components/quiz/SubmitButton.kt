package com.example.pan.presentation.views.main.lesson_page.components.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SubmitButton(
    onAnswerSubmitted: () -> Unit
) {
    val buttonColors = ButtonDefaults.elevatedButtonColors(
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ElevatedButton(
            modifier = Modifier.fillMaxWidth(0.75f),
            colors = buttonColors,
            onClick = {
                onAnswerSubmitted()
            }
        ) {
            Text(
                text = "Responder",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}