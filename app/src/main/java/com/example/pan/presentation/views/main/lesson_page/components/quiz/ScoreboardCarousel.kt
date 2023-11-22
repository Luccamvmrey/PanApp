package com.example.pan.presentation.views.main.lesson_page.components.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.example.pan.presentation.ui.theme.spacing

@Composable
fun ScoreboardCarousel(
    successScore: List<MutableState<Boolean>>,
    currentQuestionIndex: Int
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.large)
    ) {
        successScore.forEachIndexed { index, success ->
            ScoreboardItem(
                success = success.value,
                enabled = index < currentQuestionIndex
            )
        }
    }
}