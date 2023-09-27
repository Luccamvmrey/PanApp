package com.example.pan.presentation.views.main.edit_profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pan.R
import com.example.pan.core.StringConstants
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.MediumSpacer
import com.example.pan.presentation.views.components.ResponseHandler
import com.example.pan.presentation.views.components.SmallMediumSpacer
import com.example.pan.presentation.views.components.SmallSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(
        key1 = Unit
    ) {
        viewModel.getUser()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = StringConstants.EDIT_PROFILE
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                },
                actions = {
                    Text(
                        text = StringConstants.SAVE,
                        modifier = Modifier.clickable {
                            // TODO: Save user
                        }
                    )
                }
            )
        }
    ) { paddingValues ->  
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ContentHolder(
                verticalArrangement = Arrangement.Top,
                verticalPadding = MaterialTheme.spacing.medium,
                horizontalPadding = MaterialTheme.spacing.large
            ) {
                ResponseHandler(
                    response = state.getUserResponse,
                    onSuccessComposable = { data ->
                        val user = data as User

                        val model = user.photoUrl ?: R.drawable.avatar

                        AsyncImage(
                            model = model,
                            contentDescription = null,
                            modifier = Modifier
                                .size(152.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        SmallSpacer()
                        Text(
                            text = StringConstants.CHANGE_PICTURE,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.clickable {
                                onChangeProfilePicture()
                            }
                        )

                        MediumSpacer()

                        OutlinedTextField(
                            value = state.name,
                            onValueChange = {
                                viewModel.setName(it)
                            },
                            placeholder = {
                                Text(text = user.name!!)
                            },
                            isError = state.nameError.isError,
                            supportingText = {
                                if (state.nameError.isError) {
                                    Text(
                                        text = state.nameError.message,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        )

                        SmallMediumSpacer()

                        OutlinedTextField(
                            value = state.email,
                            onValueChange = {
                                viewModel.setEmail(it)
                            },
                            placeholder = {
                                Text(text = user.email!!)
                            },
                            isError = state.emailError.isError,
                            supportingText = {
                                if (state.emailError.isError) {
                                    Text(
                                        text = state.emailError.message,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        )
                    }
                )

            }
        }
    }
}

fun onChangeProfilePicture() {
    TODO("Not yet implemented")
}
