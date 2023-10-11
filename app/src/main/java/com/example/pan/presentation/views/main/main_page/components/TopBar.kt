package com.example.pan.presentation.views.main.main_page.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    configuration: TopBarConfiguration
) {
    TopAppBar(
        title = {
            Text(text = configuration.title)
        },
        actions = {
            Box(modifier = Modifier.padding(end = 16.dp)) {
                configuration.actions?.let {
                    Icon(
                        imageVector = configuration.actions,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            configuration.onActionClick?.invoke()
                        }
                    )
                }
            }
        }
    )
}