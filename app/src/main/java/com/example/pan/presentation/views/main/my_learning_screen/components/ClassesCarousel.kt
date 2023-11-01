package com.example.pan.presentation.views.main.my_learning_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pan.core.StringConstants.NEW_CLASS
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.SmallSpacer

@Composable
fun ClassesCarousel(
    classesList: List<PanClass>,
    selectedClassId: String?,
    onClassClick: (String) -> Unit,
    onCreateNew: () -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = MaterialTheme.spacing.small)
    ) {
        item {
            ClassTag(
                className = NEW_CLASS,
                isSelected = false,
                onClick = {
                    onCreateNew()
                }
            )
        }
        if (classesList.isNotEmpty()) {
            items(count = classesList.size) { index ->
                val currentClass = classesList[index]

                ClassTag(
                    className = currentClass.className!!,
                    isSelected = currentClass.classId == selectedClassId,
                    onClick = {
                        onClassClick(currentClass.classId!!)
                    }
                )
            }
        }
    }
    SmallSpacer()
    Divider()
    SmallSpacer()
}