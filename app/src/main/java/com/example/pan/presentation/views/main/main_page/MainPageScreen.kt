package com.example.pan.presentation.views.main.main_page

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.LocalLibrary
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.MESSAGES
import com.example.pan.core.StringConstants.MY_LEARNING
import com.example.pan.core.StringConstants.PROFILE
import com.example.pan.presentation.views.main.components.BottomNavigationItem

@Composable
fun MainPageScreen(
    viewModel: MainPageViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value

    val items = listOf(
        BottomNavigationItem(
            title = MY_LEARNING,
            selectedItem = Icons.Filled.LocalLibrary,
            unselectedItem = Icons.Outlined.LocalLibrary,
            hasNews = false
        ),
        BottomNavigationItem(
            title = MESSAGES,
            selectedItem = Icons.Filled.Chat,
            unselectedItem = Icons.Outlined.Chat,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = PROFILE,
            selectedItem = Icons.Filled.AccountCircle,
            unselectedItem = Icons.Outlined.AccountCircle,
        )
    )
}