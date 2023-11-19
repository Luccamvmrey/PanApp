package com.example.pan.presentation.views.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.pan.domain.models.InputError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    error: InputError = InputError(),
    singleLine: Boolean = true,
    trailingIcon: @Composable () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    hasNext: Boolean = false,
) {
    val focusManager = LocalFocusManager.current
    val keyboardActions = KeyboardActions(
        onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }
    )

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = if (hasNext) {
                ImeAction.Next
            } else {
                ImeAction.Done
            }
        ),
        keyboardActions = keyboardActions,
        label = {
            Text(
                text = labelText
            )
        },
        supportingText = {
            if (error.isError) {
                Text(
                    text = error.message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        trailingIcon = {
            trailingIcon()
        },
        singleLine = singleLine,
        modifier = modifier
    )
}