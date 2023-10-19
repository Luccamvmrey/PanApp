package com.example.pan.presentation.views.main.main_page

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.LocalLibrary
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.CLASSES_LESSONS
import com.example.pan.core.StringConstants.MY_LEARNING
import com.example.pan.core.StringConstants.PROFILE
import com.example.pan.core.delayNavigation
import com.example.pan.domain.models.classes.PanClass
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.components.ResponseHandler
import com.example.pan.presentation.views.main.components.BottomNavigationBar
import com.example.pan.presentation.views.main.components.BottomNavigationItem
import com.example.pan.presentation.views.main.main_page.components.TopBar
import com.example.pan.presentation.views.main.main_page.components.TopBarConfiguration
import com.example.pan.presentation.views.main.my_learning_screen.MyLearningScreen
import com.example.pan.presentation.views.main.my_learning_screen.components.NewClassDialog
import com.example.pan.presentation.views.main.profile_screen.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("UNCHECKED_CAST")
@Composable
fun MainPageScreen(
    viewModel: MainPageViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value

    val activity = LocalContext.current as Activity

    val items = listOf(
        BottomNavigationItem(
            title = PROFILE,
            selectedItem = Icons.Filled.AccountCircle,
            unselectedItem = Icons.Outlined.AccountCircle,
        ),
        BottomNavigationItem(
            title = MY_LEARNING,
            selectedItem = Icons.Filled.LocalLibrary,
            unselectedItem = Icons.Outlined.LocalLibrary,
            hasNews = false
        )
    )

    val profileScreenTopBar = TopBarConfiguration(
        title = PROFILE,
        actions = Icons.Outlined.Edit,
        onActionClick = {
            delayNavigation {
                navController.navigate(
                    Screen.EditProfileScreen.route
                )
            }
        }
    )
    val myLearningScreenTopBar = TopBarConfiguration(
        title = CLASSES_LESSONS,
        actions = null,
        onActionClick = null
    )
    
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(1)
    }
    var openDialog by remember {
        mutableStateOf(false)
    }

    //Only after adding some lessons

    LaunchedEffect(
        key1 = state.isReloading,
        key2 = state.user
    ) {
//        viewModel.getLessonsList()
        viewModel.getUser()
        viewModel.getClassesListFromIds()
    }

    Scaffold(
        topBar = {
            when (selectedItemIndex) {
                0 -> {
                    TopBar(
                        configuration = profileScreenTopBar
                    )
                }
                1 -> {
                    TopBar(
                        configuration = myLearningScreenTopBar
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                items = items,
                selectedItemIndex = selectedItemIndex,
                onSelectNewItem = {
                    selectedItemIndex = it
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedItemIndex) {
                0 -> { // Profile
                    ResponseHandler(
                        response = state.getUserResponse,
                        onSuccessComposable = { data ->
                            val user = data as User
                            viewModel.setUser(user)

                            ProfileScreen(
                                user = state.user!!,
                                isProfileInvisibleChecked = state.isProfileInvisibleChecked,
                                onLogout = {
                                    viewModel.signOut()
                                    activity.finishAndRemoveTask()
                                },
                                onProfileInvisibleCheck = {
                                    viewModel.setProfileInvisible(it)
                                }
                            )
                        }
                    )
                }
                1 -> { // My Learning
                    ResponseHandler(
                        response = state.getUserResponse,
                        onSuccessComposable = { user ->
                            viewModel.setUser(user as User)
                            viewModel.getClassesListFromIds()

                            ResponseHandler(
                                response = state.getClassesListResponse,
                                onSuccessComposable = { panClasses ->
                                    viewModel.setClassesList(panClasses as List<PanClass>)

                                    MyLearningScreen(
                                        user = state.user!!,
                                        selectedClassId = state.selectedClassId,
                                        classesList = state.classesList,
                                        onSelectClass = {
                                            viewModel.setSelectedClassId(it)
                                        },
                                        onNewClass = {
                                            openDialog = true
                                        }
                                    )
                                    if (openDialog) {
                                        NewClassDialog(
                                            isTeacher = user.teacher!!,
                                            viewModel = viewModel,
                                            onDismiss = { openDialog = false }
                                        )
                                    }
                                },
                                onFailureComposable = { error ->
                                    Text(text = error?.message?: "Something went wrong :/")
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}