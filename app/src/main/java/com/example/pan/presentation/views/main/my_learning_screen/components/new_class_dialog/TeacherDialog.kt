package com.example.pan.presentation.views.main.my_learning_screen.components.new_class_dialog

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.core.StringConstants
import com.example.pan.core.StringConstants.CLASS_NAME
import com.example.pan.core.StringConstants.CREATE_CLASS
import com.example.pan.core.StringConstants.DONE
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.core.StringConstants.YOUR_CLASS_ID
import com.example.pan.core.makeToast
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.views.components.ExtraSmallSpacer
import com.example.pan.presentation.views.components.MediumSpacer
import com.example.pan.presentation.views.components.PanText
import com.example.pan.presentation.views.components.PanTextField
import com.example.pan.presentation.views.components.SmallSpacer
import com.example.pan.presentation.views.main.main_page.MainPageViewModel

@Composable
fun TeacherDialog(
    viewModel: MainPageViewModel = hiltViewModel(),
    user: User
) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    var className by remember {
        mutableStateOf(NO_VALUE)
    }
    var panClass by remember {
        mutableStateOf(PanClass())
    }
    var currentState by remember {
        mutableIntStateOf(1)
    }

    when(currentState) {
        1 -> {
            PanText(
                horizontalArrangement = Arrangement.Center,
                text = StringConstants.NEW_CLASS,
                style = MaterialTheme.typography.titleMedium
            )

            SmallSpacer()

            PanTextField(
                value = className,
                onValueChange = { className = it },
                labelText = CLASS_NAME,
                error = viewModel.classNameError
            )

            SmallSpacer()

            Button(
                onClick = {
                    if (viewModel.checkCreateClass(className)) {
                        panClass = viewModel.createClass(className, user)
                        viewModel.addPanClassToFirebase(panClass)
                        currentState++
                    }
                }
            ) {
                Text(text = CREATE_CLASS)
            }
        }
        2 -> {
            AddClassToFirebase {
                viewModel.addClassIdToUser(
                    panClass.classId!!, user
                )
                currentState++
            }
        }
        3 -> {
            AddClassToUser {
                PanText(
                    horizontalArrangement = Arrangement.Center,
                    text = DONE,
                )

                ExtraSmallSpacer()

                PanText(
                    horizontalArrangement = Arrangement.Center,
                    text = YOUR_CLASS_ID,
                    style = MaterialTheme.typography.bodySmall
                )

                MediumSpacer()

                SelectionContainer {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = panClass.classId!!,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(
                            onClick = {
                                clipboardManager
                                    .setText(
                                        AnnotatedString(panClass.classId!!)
                                    )
                                makeToast(
                                    context,
                                    "Id copiado!",
                                    Toast.LENGTH_SHORT
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy to Clipboard"
                            )
                        }
                    }
                }
            }
        }
    }
}