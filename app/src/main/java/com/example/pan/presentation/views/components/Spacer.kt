package com.example.pan.presentation.views.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pan.presentation.ui.theme.spacing

@Composable
fun ExtraSmallSpacer() {
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
}

@Composable
fun SmallSpacer() {
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
}

@Composable
fun SmallMediumSpacer() {
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.smallMedium))
}

@Composable
fun MediumSpacer() {
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
}

@Composable
fun LargeSpacer() {
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
}

@Composable
fun ExtraLargeSpacer() {
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
}