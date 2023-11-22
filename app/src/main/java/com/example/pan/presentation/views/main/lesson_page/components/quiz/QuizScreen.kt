package com.example.pan.presentation.views.main.lesson_page.components.quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.pan.domain.models.lesson.Question
import com.example.pan.presentation.views.components.PanText
import com.example.pan.presentation.views.components.SmallMediumSpacer
import com.example.pan.presentation.views.components.SmallSpacer

@Composable
fun QuizScreen(
    questionsList: List<Question>,
    onFinished: (Int) -> Unit
) {
    var currentQuestionIndex by remember {
        mutableIntStateOf(0)
    }
    var selectedAnswerIndex by remember {
        mutableIntStateOf(-1)
    }
    var successCount by remember {
        mutableIntStateOf(0)
    }
    val scoreboard = List(4) {
        remember {
            mutableStateOf(false)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PanText(text = "${currentQuestionIndex + 1}ª pergunta")

        SmallSpacer()

        ScoreboardCarousel(
            successScore = scoreboard,
            currentQuestionIndex = currentQuestionIndex
        )

        SmallMediumSpacer()

        QuestionDisplay(
            question = questionsList[currentQuestionIndex],
            selectedAnswerIndex = selectedAnswerIndex,
            onAnswerSelected = {
                selectedAnswerIndex = it
            },
            onAnswerSubmitted = {
                // If the user hasn't selected an answer, don't do anything
                if (selectedAnswerIndex == -1) return@QuestionDisplay

                // If the user has selected an answer, check if it's correct
                if (
                    checkAnswer(
                        questionsList[currentQuestionIndex],
                        selectedAnswerIndex
                    )
                ) {
                    successCount++
                    scoreboard[currentQuestionIndex].value = true
                }

                // Go to next question or to results screen if there are no questions left
                when(currentQuestionIndex) {
                    questionsList.lastIndex -> {
                        onFinished(successCount)
                    }
                    else -> {
                        currentQuestionIndex++
                        selectedAnswerIndex = -1
                    }
                }
            }
        )
    }
}

fun checkAnswer(
    question: Question,
    selectedAnswerIndex: Int
): Boolean {
    return selectedAnswerIndex == question.correctAnswerIndex
}