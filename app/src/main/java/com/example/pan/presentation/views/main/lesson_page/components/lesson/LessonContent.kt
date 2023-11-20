package com.example.pan.presentation.views.main.lesson_page.components.lesson

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun LessonContent(
    lessonContent: String
) {
    Card(
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Text(
                text = lessonContent,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}