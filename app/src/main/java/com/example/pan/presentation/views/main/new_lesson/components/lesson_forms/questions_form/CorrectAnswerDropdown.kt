package com.example.pan.presentation.views.main.new_lesson.components.lesson_forms.questions_form

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pan.core.StringConstants.CORRECT_ANSWER

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CorrectAnswerDropdown(
    currentValue: String,
    onValueChange: (String) -> Unit,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    val value = when (currentValue) {
        "-1" -> "-"
        else -> currentValue
    }

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
                Text(text = CORRECT_ANSWER)
            },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                onExpandedChange(false)
            }
        ) {
            for (i in 1..4) {
                DropdownMenuItem(
                    text = {
                        Text(text = i.toString())
                    },
                    onClick = {
                        onValueChange(i.toString())
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}