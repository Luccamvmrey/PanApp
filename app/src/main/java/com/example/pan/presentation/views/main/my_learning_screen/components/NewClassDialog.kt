package com.example.pan.presentation.views.main.my_learning_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import com.example.pan.core.StringConstants.CLASS_NAME
import com.example.pan.core.StringConstants.CLASS_NAME_MUST_NOT_BE_EMPTY
import com.example.pan.core.StringConstants.CREATE_CLASS
import com.example.pan.core.StringConstants.DONE
import com.example.pan.core.StringConstants.NEW_CLASS
import com.example.pan.core.StringConstants.YOUR_CLASS_ID
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.Response.Loading
import com.example.pan.domain.models.Response.Success
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.presentation.views.components.ExtraSmallSpacer
import com.example.pan.presentation.views.components.PanProgressBar
import com.example.pan.presentation.views.components.PanTextField
import com.example.pan.presentation.views.components.SmallSpacer
import com.example.pan.presentation.views.main.main_page.MainPageViewModel

@Composable
fun NewClassDialog(
    isTeacher: Boolean,
    viewModel: MainPageViewModel,
    onDismiss: () -> Unit,
) {
    val state = viewModel.state.collectAsState().value
    var className by remember {
        mutableStateOf("")
    }
    var classNameError by remember {
        mutableStateOf(InputError())
    }
    var panClass by remember {
        mutableStateOf(PanClass())
    }
    var isClassCreated by remember {
        mutableStateOf(false)
    }

    var classId by remember {
        mutableStateOf("")
    }
    var classIdError by remember {
        mutableStateOf(InputError())
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Card (
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                if (isTeacher) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = NEW_CLASS,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                    SmallSpacer()

                    if (!isClassCreated) {
                        PanTextField(
                            value = className,
                            onValueChange = { className = it },
                            labelText = CLASS_NAME,
                            error = classNameError
                        )

                        SmallSpacer()

                        Button(
                            onClick = {
                                classNameError = InputError()

                                if (className.isEmpty()) {
                                    classNameError = InputError(
                                        true,
                                        CLASS_NAME_MUST_NOT_BE_EMPTY
                                    )
                                    return@Button
                                }

                                panClass = viewModel.createClass(className)
                                viewModel.addPanClassToFirebase(panClass)

                                when (state.createClassResponse) {
                                    is Loading -> isLoading = true
                                    is Success -> {
                                        viewModel.addClassIdToUser(panClass.classId!!)

                                        when (state.updateUserResponse) {
                                            is Loading -> isLoading = true
                                            is Success -> {
                                                isClassCreated = true
                                                isLoading = false
                                            }
                                            else -> {}
                                        }
                                    }
                                    else -> {}
                                }
                            }
                        ) {
                            Text(text = CREATE_CLASS)
                        }
                        if (isLoading) {
                            Surface (
                                modifier = Modifier
                                    .fillMaxSize()
                                    .zIndex(1f)
                            ) {
                                PanProgressBar()
                            }
                        }
                    } else {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = DONE)
                        }
                        ExtraSmallSpacer()
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = YOUR_CLASS_ID,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = panClass.classId!!)
                        }
                    }

                } else {
                    Text(text = "I'm a student!")
                }
            }
        }
    }
}