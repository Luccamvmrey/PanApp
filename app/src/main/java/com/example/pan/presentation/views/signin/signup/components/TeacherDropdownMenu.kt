package com.example.pan.presentation.views.signin.signup.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pan.core.StringConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherDropdownMenu(
    isTeacher: Boolean,
    isExpanded: Boolean,
    onTeacherChange: (Boolean) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
) {
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            onExpandedChange(it)
        }
    ) {
        OutlinedTextField(
            value = if (isTeacher) StringConstants.YES else StringConstants.NO,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            label = {
                Text(text = StringConstants.ARE_YOU_A_TEACHER)
            },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                onExpandedChange(false)
            }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = StringConstants.YES)
                },
                onClick = {
                    onTeacherChange(true)
                    onExpandedChange(false)
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = StringConstants.NO)
                },
                onClick = {
                    onTeacherChange(false)
                    onExpandedChange(false)
                }
            )
        }
    }
}