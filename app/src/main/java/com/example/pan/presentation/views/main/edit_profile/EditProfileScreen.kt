package com.example.pan.presentation.views.main.edit_profile

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.main.edit_profile.components.EditProfileForm
import com.example.pan.presentation.views.main.edit_profile.components.EditProfileTopBar
import com.example.pan.presentation.views.main.edit_profile.components.GetUser
import com.example.pan.presentation.views.main.edit_profile.components.UpdateUser
import com.example.pan.presentation.views.main.edit_profile.components.UploadImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = hiltViewModel(),
    navController: NavController
) {
    var name by remember {
        mutableStateOf(NO_VALUE)
    }
    var email by remember {
        mutableStateOf(NO_VALUE)
    }
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    Scaffold(
        topBar = {
            EditProfileTopBar(
                name = name,
                email = email,
                bitmap = bitmap,
                navController = navController
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                GetUser { user ->
                    viewModel.currentUser = user
                    name = user.name!!
                    email = user.email!!

                    EditProfileForm(
                        name = name,
                        onNameChange = {
                            name = it
                        },
                        email = email,
                        onEmailChange = {
                            email = it
                        },
                        bitmap = bitmap,
                        onBitmapChange = {
                            bitmap = it
                        },
                        user = user
                    )
                }
                UploadImage { url ->
                    val newUser = viewModel.currentUser.copy(
                        name = name,
                        email = email,
                        photoUrl = url
                    )

                    viewModel.updateUser(newUser)
                }
                UpdateUser {
                    navController.navigate(
                        Screen.MainPageScreen.route
                    )
                }
            }
        }
    )
}
