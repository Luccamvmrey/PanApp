package com.example.pan.presentation.views.main.main_page.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.pan.core.StringConstants

data class TopBarConfiguration(
    val title: String,
    val actions: ImageVector?,
)

val myLearningScreenTopBar = TopBarConfiguration(
    title = StringConstants.CLASSES_LESSONS,
    actions = null,
)

val profileScreenTopBar = TopBarConfiguration(
    title = StringConstants.PROFILE,
    actions = Icons.Outlined.Edit,
)