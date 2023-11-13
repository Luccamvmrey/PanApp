package com.example.pan.presentation.views.main.new_lesson.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pan.core.StringConstants.PREREQUISITES
import com.example.pan.domain.models.lesson.Lesson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrerequisitesDropdownMenu(
    currentValue: String,
    onValueChange: (String) -> Unit,
    lessonsList: List<Lesson>,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
) {
    val value = currentValue.ifEmpty { "Nenhum" }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            onExpandedChange(it)
        }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            label = {
                Text(text = PREREQUISITES)
            },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                onExpandedChange(false)
            }
        ) {
            lessonsList.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it.lessonTitle!!)
                    },
                    onClick = {
                        onValueChange(it.lessonTitle!!)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}