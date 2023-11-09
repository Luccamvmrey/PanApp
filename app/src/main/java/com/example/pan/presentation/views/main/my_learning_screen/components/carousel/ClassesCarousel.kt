package com.example.pan.presentation.views.main.my_learning_screen.components.carousel

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.pan.core.delayNavigation
import com.example.pan.core.makeToast
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.components.SmallSpacer

@Composable
fun ClassesCarousel(
    classesList: List<PanClass>,
    selectedClassId: String,
    user: User,
    navController: NavController,
    onClassClick: (String) -> Unit,
    onCreateNew: () -> Unit
) {
    val context = LocalContext.current

    ClassesList(
        classesList = classesList,
        selectedClassId = selectedClassId,
        onClassClick = {
            onClassClick(it)
        },
        onCreateNew = {
            onCreateNew()
        }
    )

    when {
        user.teacher!! -> {
            DividerTextButton(
                onClick = {
                    if (selectedClassId.isEmpty()) {
                        makeToast(
                            context,
                            "Selecione uma turma",
                        )
                        return@DividerTextButton
                    }

                    delayNavigation {
                        navController.navigate(
                            Screen.NewLessonScreen.route + "/$selectedClassId"
                        )
                    }
                }
            )
        }

        else -> {
            SmallSpacer()
            Divider()
        }
    }

    SmallSpacer()
}