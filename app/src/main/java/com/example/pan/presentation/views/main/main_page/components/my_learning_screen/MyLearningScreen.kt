package com.example.pan.presentation.views.main.main_page.components.my_learning_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.main.main_page.MainPageViewModel

@Composable
fun MyLearningScreen(
    user: User,
    selectedClassId: String?,
    classesList: List<PanClass>?,
) {

    ContentHolder(
        verticalArrangement = Arrangement.Top,
        verticalPadding = MaterialTheme.spacing.medium,
        horizontalPadding = MaterialTheme.spacing.large
    ) {

    }
}