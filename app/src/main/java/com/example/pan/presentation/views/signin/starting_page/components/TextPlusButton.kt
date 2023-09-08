package com.example.pan.presentation.views.signin.starting_page.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pan.presentation.views.components.ExtraSmallSpacer

@Composable
fun TextPlusButton(
    text: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
    
    ExtraSmallSpacer()
    
    OutlinedButton(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = buttonText,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}