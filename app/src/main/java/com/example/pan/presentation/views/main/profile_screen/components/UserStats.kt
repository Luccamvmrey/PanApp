package com.example.pan.presentation.views.main.profile_screen.components

import androidx.compose.runtime.Composable
import com.example.pan.core.StringConstants
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.views.components.SmallSpacer

@Composable
fun UserStats(
    user: User
) {
    val formattedCompletedLessons = user.completedLessons?.size ?: 0

    ProfileScreenCard(
        title = StringConstants.WATCHED_LESSONS,
        text = formattedCompletedLessons.toString()
    )

    SmallSpacer()

    ProfileScreenCard(
        title = StringConstants.POINTS,
        text = user.points.toString()
    )
}