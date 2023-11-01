package com.example.pan.presentation.views.signin.signup.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import com.example.pan.core.StringConstants.EMAIL
import com.example.pan.core.StringConstants.NAME
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.core.StringConstants.PASSWORD
import com.example.pan.core.StringConstants.SIGNUP
import com.example.pan.domain.models.InputError
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.views.components.ExtraLargeSpacer
import com.example.pan.presentation.views.components.MediumSpacer
import com.example.pan.presentation.views.components.PanPasswordTextField
import com.example.pan.presentation.views.components.PanTextField

@Composable
fun SignUpForm(
    nameError: InputError,
    emailError: InputError,
    passwordError: InputError,
    onSignup: (User, String) -> Unit,
) {
    var name by remember { mutableStateOf(NO_VALUE) }
    var email by remember { mutableStateOf(NO_VALUE) }
    var password by remember { mutableStateOf(NO_VALUE) }
    var isTeacher by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }

    PanTextField(
        value = name,
        onValueChange = { name = it },
        labelText = NAME,
        error = nameError
    )

    MediumSpacer()

    PanTextField(
        value = email,
        onValueChange = { email = it },
        labelText = EMAIL,
        error = emailError,
        keyboardType = KeyboardType.Email
    )

    MediumSpacer()

    PanPasswordTextField(
        value = password,
        onValueChange = { password = it },
        labelText = PASSWORD,
        error = passwordError
    )

    MediumSpacer()

    TeacherDropdownMenu(
        isTeacher = isTeacher,
        isExpanded = isExpanded,
        onTeacherChange = { isTeacher = it },
        onExpandedChange = { isExpanded = it }
    )

    ExtraLargeSpacer()

    Button(
        onClick = {
            onSignup(
                User(
                    name = name,
                    email = email,
                    teacher = isTeacher
                ),
                password
            )
        }
    ) {
        Text(text = SIGNUP)
    }
}