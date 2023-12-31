package com.example.pan.presentation.views.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    extraTitle: String? = null,
    configuration: TopBarConfiguration,
    onAction: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(text = configuration.title + (extraTitle ?: ""))
        },
        actions = {
            IconButton(
                onClick = {
                    onAction?.invoke()
                }
            ) {
                configuration.actions?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null
                    )
                }
            }
        }
    )
}