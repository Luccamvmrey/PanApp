package com.example.pan.presentation.views.main.my_learning_screen.components.new_class_dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import com.example.pan.domain.models.user.User

@Composable
fun NewClassDialog(
    onDismissRequest: () -> Unit,
    user: User
) {
    Dialog(
        onDismissRequest = {
            onDismissRequest()
        }
    ) {
        NewClassDialogCard {
            NewClassDialogContent(
                isTeacher = user.teacher!!,
                isTeacherContent = {
                    TeacherDialog(
                        user = user
                    )
                },
                isStudentContent = {
                    StudentDialog(
                        user = user,
                        onDismissRequest = {
                            onDismissRequest()
                        }
                    )
                },
            )
        }
    }
}