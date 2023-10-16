package com.example.pan.presentation.views.main.profile_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.ExtraSmallSpacer

@Composable
fun ProfileScreenCard(
    title: String,
    text: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.smallMedium,
                    vertical = MaterialTheme.spacing.small
                )
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            ExtraSmallSpacer()

            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}