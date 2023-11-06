package com.example.pan.presentation.views.main.my_learning_screen.components.new_class_dialog

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.views.main.main_page.MainPageViewModel

@Composable
fun StudentDialog(
    viewModel: MainPageViewModel = hiltViewModel(),
    user: User
) {
    Text(text = "Hey, I'm a student!")
    Text(text = "My name is ${user.name}")
}