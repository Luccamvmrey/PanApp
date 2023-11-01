package com.example.pan.presentation.views.main.profile_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.pan.core.StringConstants
import com.example.pan.presentation.views.components.PanText
import com.example.pan.presentation.views.components.SmallSpacer

@Composable
fun NameAndEmail(
    name: String,
    email: String
) {
    PanText(
        horizontalArrangement = Arrangement.Start,
        text = StringConstants.WELCOME_USER.format(name),
        style = MaterialTheme.typography.titleMedium
    )

    SmallSpacer()

    PanText(
        horizontalArrangement = Arrangement.Start,
        text = email,
        style = MaterialTheme.typography.bodyMedium
    )
}