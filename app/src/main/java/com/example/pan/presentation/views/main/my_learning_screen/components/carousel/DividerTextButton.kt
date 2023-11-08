package com.example.pan.presentation.views.main.my_learning_screen.components.carousel

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pan.core.StringConstants.NEW_LESSON

@Composable
fun DividerTextButton(
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Divider(modifier = Modifier.weight(1f))

        TextButton(
            modifier = Modifier
                .padding(0.dp),
            onClick = {
                onClick()
            },
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Add class",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = NEW_LESSON,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}