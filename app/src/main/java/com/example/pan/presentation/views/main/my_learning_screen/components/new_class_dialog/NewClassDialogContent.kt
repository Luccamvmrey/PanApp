package com.example.pan.presentation.views.main.my_learning_screen.components.new_class_dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NewClassDialogContent(
    isTeacher: Boolean,
    isTeacherContent: @Composable () -> Unit,
    isStudentContent: @Composable () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            isTeacher -> {
                isTeacherContent()
            }
            else -> {
                isStudentContent()
            }
        }
    }
}