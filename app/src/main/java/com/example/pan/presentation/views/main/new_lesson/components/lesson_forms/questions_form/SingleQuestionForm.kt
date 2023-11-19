package com.example.pan.presentation.views.main.new_lesson.components.lesson_forms.questions_form

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.pan.core.StringConstants.ADD
import com.example.pan.core.StringConstants.NEXT
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.core.StringConstants.QUESTION_TEXT
import com.example.pan.domain.models.lesson.Question
import com.example.pan.presentation.views.components.MediumSpacer
import com.example.pan.presentation.views.components.PanText
import com.example.pan.presentation.views.components.PanTextField
import com.example.pan.presentation.views.components.SmallSpacer

@Composable
fun SingleQuestionForm(
    currentQuestion: Int,
    onSubmit: (Question) -> Unit = {}
) {
    var questionText by remember {
        mutableStateOf(NO_VALUE)
    }
    val answers = List(4) {
        remember {
            mutableStateOf(NO_VALUE)
        }
    }
    var correctAnswer by remember {
        mutableStateOf("-1")
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var errorText by remember {
        mutableStateOf(NO_VALUE)
    }

    PanText(
        text = "Pergunta ${currentQuestion + 1} de 4",
        style = MaterialTheme.typography.bodySmall
    )

    PanTextField(
        value = questionText,
        onValueChange = { questionText = it },
        labelText = QUESTION_TEXT,
        singleLine = false
    )

    SmallSpacer()

    CorrectAnswerDropdown(
        currentValue = correctAnswer,
        onValueChange = { correctAnswer = it },
        isExpanded = isExpanded,
        onExpandedChange = {
            isExpanded = it
        }
    )

    MediumSpacer()

    answers.forEachIndexed { index, answer ->
        PanTextField(
            value = answer.value,
            onValueChange = { answers[index].value = it },
            labelText = "Resposta ${index + 1}",
            trailingIcon = {
                if (correctAnswer.toInt() == index + 1) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Correct Answer")
                }
            },
            singleLine = false,
            hasNext = index != answers.lastIndex
        )
    }

    MediumSpacer()

    if (errorText.isNotEmpty()) {
        PanText(
            text = errorText,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.error
        )
    }

    Button(
        onClick = {
            errorText = NO_VALUE
            if (
                questionText.isEmpty() ||
                correctAnswer == "-1" ||
                answers.any { it.value.isEmpty() }
            ) {
                errorText = "Preencha todos os campos"
            } else {
                onSubmit(
                    Question(
                        questionText = questionText,
                        answers = answers.map { it.value },
                        correctAnswerIndex = correctAnswer.toInt()
                    )
                )

                questionText = NO_VALUE
                answers.forEach { it.value = NO_VALUE }
                correctAnswer = "-1"
            }
        }
    ) {
        Text(
            text = if (currentQuestion == 3) ADD else NEXT
        )
    }
}