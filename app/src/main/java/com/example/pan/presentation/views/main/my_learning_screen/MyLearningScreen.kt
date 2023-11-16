package com.example.pan.presentation.views.main.my_learning_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.main.my_learning_screen.components.carousel.ClassesCarousel
import com.example.pan.presentation.views.main.my_learning_screen.components.MyLearningContent
import com.example.pan.presentation.views.main.my_learning_screen.components.new_class_dialog.NewClassDialog

@Composable
fun MyLearningScreen(
    user: User,
    classesList: List<PanClass>,
    navController: NavController,
) {
    var selectedClassId by rememberSaveable {
        mutableStateOf(NO_VALUE)
    }
    var openDialog by remember {
        mutableStateOf(false)
    }

    ContentHolder(
        verticalArrangement = Arrangement.Top,
        verticalPadding = MaterialTheme.spacing.small,
        horizontalPadding = MaterialTheme.spacing.small,
    ) {
        ClassesCarousel(
            classesList = classesList,
            selectedClassId = selectedClassId,
            user = user,
            navController = navController,
            onClassClick = {
                selectedClassId = it
            },
            onCreateNew = {
                openDialog = true
            }
        )

        MyLearningContent(
            selectedClassId = selectedClassId,
            user = user
        )
    }
    if (openDialog) {
        NewClassDialog(
            onDismissRequest = {
                openDialog = false
            },
            user = user
        )
    }
}

