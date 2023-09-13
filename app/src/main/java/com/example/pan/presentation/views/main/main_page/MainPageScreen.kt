package com.example.pan.presentation.views.main.main_page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MainPageScreen(
    viewModel: MainPageViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value
}