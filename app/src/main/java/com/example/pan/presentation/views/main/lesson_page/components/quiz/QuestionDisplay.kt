package com.example.pan.presentation.views.main.lesson_page.components.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pan.domain.models.lesson.Question
import com.example.pan.presentation.views.components.MediumSpacer
import com.example.pan.presentation.views.components.SmallSpacer

@Composable
fun QuestionDisplay(
    question: Question,
    selectedAnswerIndex: Int,
    onAnswerSelected: (Int) -> Unit,
    onAnswerSubmitted: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 48.dp)
        ) {
            Text(
                text = question.questionText!!,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }

    MediumSpacer()

    question.answers?.forEachIndexed { index, answer ->
        Answer(
            answerText = answer,
            isSelected = index == selectedAnswerIndex,
            onAnswerSelected = {
                onAnswerSelected(index)
            }
        )
        when (index) {
            question.answers.size - 1 -> {
                MediumSpacer()
            }
            else -> SmallSpacer()
        }
    }

    SubmitButton(
        onAnswerSubmitted = {
            onAnswerSubmitted()
        }
    )
}