package com.example.pan.presentation.views.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.LocalLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.pan.core.StringConstants

data class BottomNavigationItem(
    val title: String,
    val selectedItem: ImageVector,
    val unselectedItem: ImageVector,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        title = StringConstants.PROFILE,
        selectedItem = Icons.Filled.AccountCircle,
        unselectedItem = Icons.Outlined.AccountCircle,
    ),
    BottomNavigationItem(
        title = StringConstants.MY_LEARNING,
        selectedItem = Icons.Filled.LocalLibrary,
        unselectedItem = Icons.Outlined.LocalLibrary,
        hasNews = false
    )
)