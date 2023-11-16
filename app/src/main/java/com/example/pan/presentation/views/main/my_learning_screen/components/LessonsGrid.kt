package com.example.pan.presentation.views.main.my_learning_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.pan.core.makeToast
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.presentation.ui.theme.spacing

@Composable
fun LessonsGrid(
    lessons: List<Lesson>
) {
    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        items(lessons) { lesson ->
            LessonCard(
                lesson = lesson,
                onClick = {
                    makeToast(
                        context,
                        "Aula ${lesson.order}",
                    )
                }
            )
        }
    }
}