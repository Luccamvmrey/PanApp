package com.example.pan.presentation.views.main.new_lesson.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.pan.domain.models.lesson.Lesson

@Composable
fun NewLessonForm(
    onSubmit: (Lesson) -> Unit
) {
    var lesson by remember {
        mutableStateOf(Lesson())
    }

    var currentScreen by remember {
        mutableIntStateOf(0)
    }

    when(currentScreen) {
        0 -> {
            FirstForm { lessonDraft ->
                lesson = lessonDraft
                currentScreen++
            }
        }
        1 -> {

        }
    }


}