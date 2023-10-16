package com.example.pan.presentation.views.main.my_learning_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.pan.core.StringConstants.NEW_CLASS_TITLE
import com.example.pan.presentation.views.main.main_page.MainPageViewModel

@Composable
fun NewClassDialog(
    isTeacher: Boolean,
    viewModel: MainPageViewModel,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    var className by remember {
        mutableStateOf("")
    }
    var classId by remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Card (
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(200.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                if (isTeacher) {
                    Text(text = NEW_CLASS_TITLE)
                } else {
                    Text(text = "I'm a student!")
                }
            }
        }
    }
}