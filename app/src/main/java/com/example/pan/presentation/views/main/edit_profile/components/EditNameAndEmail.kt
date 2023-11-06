package com.example.pan.presentation.views.main.edit_profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.core.StringConstants.CHANGE_EMAIL
import com.example.pan.core.StringConstants.CHANGE_NAME
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.views.components.PanText
import com.example.pan.presentation.views.components.SmallMediumSpacer
import com.example.pan.presentation.views.components.SmallSpacer
import com.example.pan.presentation.views.main.edit_profile.EditProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNameAndEmail(
    viewModel: EditProfileViewModel = hiltViewModel(),
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    user: User
) {
    PanText(
        horizontalArrangement = Arrangement.Start,
        text = CHANGE_NAME,
        style = MaterialTheme.typography.titleSmall
    )
    SmallSpacer()

    OutlinedTextField(
        value = name,
        onValueChange = {
            onNameChange(it)
        },
        placeholder = {
            Text(text = user.name!!)
        },
        isError = viewModel.nameError.isError,
        supportingText = {
            if (viewModel.nameError.isError) {
                Text(
                    text = viewModel.nameError.message,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    )

    SmallMediumSpacer()

    PanText(
        horizontalArrangement = Arrangement.Start,
        text = CHANGE_EMAIL,
        style = MaterialTheme.typography.titleSmall
    )
    SmallSpacer()

    OutlinedTextField(
        value = email,
        onValueChange = {
            onEmailChange(it)
        },
        placeholder = {
            Text(text = user.email!!)
        },
        isError = viewModel.emailError.isError,
        supportingText = {
            if (viewModel.emailError.isError) {
                Text(
                    text = viewModel.emailError.message,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    )
}