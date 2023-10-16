package com.example.pan.presentation.views.main.my_learning_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pan.core.StringConstants.SELECT_A_CLASS
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.SmallSpacer
import com.example.pan.presentation.views.main.my_learning_screen.components.ClassesLazyRow

@Composable
fun MyLearningScreen(
    user: User,
    selectedClassId: String,
    classesList: List<PanClass>,
    onSelectClass: (String) -> Unit,
    onNewClass: () -> Unit
) {
    ContentHolder(
        verticalArrangement = Arrangement.Top,
        verticalPadding = MaterialTheme.spacing.small,
        horizontalPadding = MaterialTheme.spacing.small,
    ) {
        ClassesLazyRow(
            classesList = classesList,
            selectedClassId = selectedClassId,
            onClassClick = {
                onSelectClass(it)
            },
            onCreateNew = {
                onNewClass()
            }
        )
        SmallSpacer()
        Divider()
        SmallSpacer()

        if (selectedClassId.isEmpty()) {
            SmallSpacer()

            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium)
            ) {
                Text(
                    text = SELECT_A_CLASS,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        } else {
            //TODO
        }
    }
}

