package com.example.pan.presentation.views.main.lesson_page.components.lesson

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.pan.core.StringConstants.GO_TO_QUESTIONS
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.presentation.views.components.SmallMediumSpacer
import com.example.pan.presentation.views.main.lesson_page.components.ScrollableColumn
import com.example.pan.presentation.views.main.lesson_page.components.quiz.QuizScreen
import com.example.pan.presentation.views.main.lesson_page.components.results.ResultsScreen

@Composable
fun LessonDisplay(
    lesson: Lesson,
    onContinue: (Int) -> Unit
) {
    var currentState by remember {
        mutableIntStateOf(0)
    }
    var successCount by remember {
        mutableIntStateOf(0)
    }

    when(currentState) {
        0 -> {
            ScrollableColumn {
                if (lesson.videoUrl!!.isNotEmpty()) {
                    VideoDisplay(videoUrl = lesson.videoUrl)
                    SmallMediumSpacer()
                }

                LessonContent(lessonContent = lesson.lessonText!!)

                Button(
                    onClick = {
                        currentState++
                    }
                ) {
                    Text(text = GO_TO_QUESTIONS)
                }
            }
        }
        1 -> {
            QuizScreen(
                questionsList = lesson.questions!!,
                onFinished = { correctAnswers ->
                    successCount = correctAnswers
                    currentState++
                }
            )
        }
        2 -> {
            ResultsScreen(
                successCount = successCount,
                totalQuestions = lesson.questions!!.size,
                onContinue = {
                    onContinue(successCount)
                }
            )
        }
    }
}
