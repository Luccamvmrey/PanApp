package com.example.pan.presentation.views.main.edit_profile

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pan.R
import com.example.pan.core.StringConstants
import com.example.pan.core.StringConstants.CHANGE_EMAIL
import com.example.pan.core.StringConstants.CHANGE_NAME
import com.example.pan.core.StringConstants.CHANGE_PICTURE
import com.example.pan.core.delayNavigation
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.MediumSpacer
import com.example.pan.presentation.views.components.ResponseHandler
import com.example.pan.presentation.views.components.SmallMediumSpacer
import com.example.pan.presentation.views.components.SmallSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("DEPRECATION")
@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.setImageUri(uri!!)
    }

    state.imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            viewModel.setBitmap(
                MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            )

        } else {
            val source = ImageDecoder
                .createSource(context.contentResolver, it)
            viewModel.setBitmap(ImageDecoder.decodeBitmap(source))

        }
    }

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
                            if (viewModel.checkFields()) {
                                viewModel.uploadUserProfilePicture()
                            }
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
                if (!state.isUploadingImage) {
                    ResponseHandler(
                        response = state.getUserResponse,
                        onSuccessComposable = { data ->
                            val user = data as User

                            viewModel.setUser(user)

                            val model: Any = if (state.bitmap != null) {
                                state.bitmap!!
                            } else {
                                user.photoUrl ?: R.drawable.avatar
                            }

                            viewModel.setName(user.name!!)
                            viewModel.setEmail(user.email!!)

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
                                text = CHANGE_PICTURE,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.clickable {
                                    launcher.launch("image/*")
                                }
                            )

                            MediumSpacer()

                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .fillMaxWidth(),
                            ) {
                                Text(
                                    text = CHANGE_NAME,
                                    style = MaterialTheme.typography.titleSmall,
                                )
                            }

                            SmallSpacer()

                            OutlinedTextField(
                                value = state.name,
                                onValueChange = {
                                    viewModel.setName(it)
                                },
                                placeholder = {
                                    Text(text = user.name)
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

                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .fillMaxWidth(),
                            ) {
                                Text(
                                    text = CHANGE_EMAIL,
                                    style = MaterialTheme.typography.titleSmall,
                                )
                            }

                            SmallSpacer()

                            OutlinedTextField(
                                value = state.email,
                                onValueChange = {
                                    viewModel.setEmail(it)
                                },
                                placeholder = {
                                    Text(text = user.email)
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
                } else {
                    ResponseHandler(
                        response = state.uploadProfilePictureResponse,
                        onSuccessComposable = { data ->
                            val url = data as String

                            Log.d("URL", url)
                            val user = User(
                                userId = state.user!!.userId,
                                name = state.name,
                                email = state.email,
                                completedLessons = state.user!!.completedLessons,
                                points = state.user!!.points,
                                photoUrl = url,
                                isTeacher = state.user!!.isTeacher
                            )

                            viewModel.updateUser(user)
                            ResponseHandler(
                                response = state.updateUserResponse,
                                onSuccess = {
                                    delayNavigation {
                                        navController.navigate(
                                            Screen.MainPageScreen.route
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
}
