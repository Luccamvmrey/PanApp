package com.example.pan.presentation.views.main.my_learning_screen.components.new_class_dialog

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.core.StringConstants
import com.example.pan.core.StringConstants.CLASS_DOES_NOT_EXIST
import com.example.pan.core.StringConstants.CLASS_ID
import com.example.pan.core.StringConstants.ENTER_CLASS
import com.example.pan.core.StringConstants.INSERT_CLASS_ID
import com.example.pan.core.StringConstants.NEW_CLASS
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.views.components.ExtraSmallSpacer
import com.example.pan.presentation.views.components.PanText
import com.example.pan.presentation.views.components.PanTextField
import com.example.pan.presentation.views.components.SmallSpacer
import com.example.pan.presentation.views.main.main_page.MainPageViewModel

@Composable
fun StudentDialog(
    viewModel: MainPageViewModel = hiltViewModel(),
    user: User,
    onDismissRequest: () -> Unit,
) {
    var classId by remember {
        mutableStateOf(NO_VALUE)
    }
    var currentState by remember {
        mutableIntStateOf(1)
    }

    when(currentState) {
        1 -> {
            PanText(
                horizontalArrangement = Arrangement.Center,
                text = NEW_CLASS,
                style = MaterialTheme.typography.titleMedium
            )

            SmallSpacer()

            PanText(
                horizontalArrangement = Arrangement.Center,
                text = INSERT_CLASS_ID,
                style = MaterialTheme.typography.bodySmall
            )

            PanTextField(
                value = classId,
                onValueChange = { classId = it } ,
                labelText = CLASS_ID,
                error = viewModel.classIdError
            )

            Button(
                onClick = {
                    if (viewModel.checkClassId(classId)) {
                        viewModel.addStudentToClass(classId, user)
                        currentState++
                    }
                }
            ) {
                Text(text = ENTER_CLASS)
            }
        }
        2 -> {
            AddStudent { classExists ->
                if (classExists) {
                    viewModel.addClassIdToUser(classId, user)
                    currentState++
                } else {
                    Text(text = CLASS_DOES_NOT_EXIST)
                }
            }
        }
        3 -> {
            AddClassToUser {
                PanText(
                    horizontalArrangement = Arrangement.Center,
                    text = StringConstants.DONE,
                )

                ExtraSmallSpacer()

                PanText(
                    horizontalArrangement = Arrangement.Center,
                    text = StringConstants.CLASS_ENTERED,
                    style = MaterialTheme.typography.bodySmall
                )

                Handler(Looper.getMainLooper()).postDelayed({
                    onDismissRequest()
                }, 2000)
            }
        }
    }
}