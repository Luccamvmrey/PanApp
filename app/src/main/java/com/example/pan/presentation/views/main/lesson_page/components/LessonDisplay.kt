package com.example.pan.presentation.views.main.lesson_page.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.pan.domain.models.lesson.Lesson

@Composable
fun LessonDisplay(
    lesson: Lesson,
    navController: NavController
) {
    ScrollableColumn {
        if (lesson.videoUrl!!.isNotEmpty()) {
            Log.d("VIDEO PLAYER", "LessonDisplay: videoUrl is not empty: ${lesson.videoUrl}")
            VideoPlayer(
                videoUrl = lesson.videoUrl
            )
        }
    }
}
