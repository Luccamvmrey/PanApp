package com.example.pan.presentation.views.main.edit_profile.components

import android.graphics.Bitmap
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.SaveAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.EDIT_PROFILE
import com.example.pan.core.StringConstants.SAVE
import com.example.pan.presentation.views.main.edit_profile.EditProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileTopBar(
    name: String,
    email: String,
    bitmap: Bitmap?,
    navController: NavController,
    viewModel: EditProfileViewModel = hiltViewModel()
) {
    TopAppBar(
        title = {
            Text(text = EDIT_PROFILE)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back"
                )
            }
        },
        actions = {
            TextButton(
                onClick = {
                    if (viewModel.checkFields(name, email)) {
                        if (bitmap != null) {
                            viewModel.uploadUserProfilePicture(bitmap)
                        } else {
                            viewModel.updateUser(
                                name = name,
                                email = email
                            )
                        }
                    }
                }
            ) {
                Icon(imageVector = Icons.Rounded.SaveAlt, contentDescription = "Save")
                Text(text = SAVE)
            }
        }
    )
}