package com.example.pan.presentation.views.main.new_lesson.components.lesson_forms.questions_form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.pan.domain.models.lesson.Question

@Composable
fun QuestionsForm(
    onSubmit: (List<Question>) -> Unit
) {
    var currentQuestion by remember {
        mutableIntStateOf(0)
    }
    val questions = List(4) {
        remember {
            mutableStateOf(Question())
        }
    }

    SingleQuestionForm(
        currentQuestion = currentQuestion
    ) { question ->
        questions[currentQuestion].value = question

        when (currentQuestion) {
            questions.lastIndex -> {
                onSubmit(questions.map { it.value })
            }
            else -> {
                currentQuestion++
            }
        }
    }
}