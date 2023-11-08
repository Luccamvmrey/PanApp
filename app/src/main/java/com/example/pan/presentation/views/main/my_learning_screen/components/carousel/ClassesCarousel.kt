package com.example.pan.presentation.views.main.my_learning_screen.components.carousel

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.pan.core.makeToast
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.views.components.SmallSpacer

@Composable
fun ClassesCarousel(
    classesList: List<PanClass>,
    selectedClassId: String,
    onClassClick: (String) -> Unit,
    onCreateNew: () -> Unit,
    user: User
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

    if (user.teacher!!) {
        DividerTextButton(
            onClick = {
                if (selectedClassId.isEmpty()) {
                    makeToast(
                        context,
                        "Selecione uma turma",
                    )
                    return@DividerTextButton
                }
            }
        )
    } else {
        SmallSpacer()
        Divider()
    }


    SmallSpacer()
}