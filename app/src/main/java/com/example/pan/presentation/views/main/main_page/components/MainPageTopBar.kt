package com.example.pan.presentation.views.main.main_page.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.pan.core.delayNavigation
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.components.TopBar
import com.example.pan.presentation.views.components.myLearningScreenTopBar
import com.example.pan.presentation.views.components.profileScreenTopBar

@Composable
fun MainPageTopBar(
    selectedScreenIndex: Int,
    navController: NavController
) {
    when (selectedScreenIndex) {
        0 -> {
            TopBar(
                configuration = profileScreenTopBar,
                onAction = {
                    delayNavigation {
                        navController.navigate(
                            Screen.EditProfileScreen.route
                        )
                    }
                }
            )
        }
        1 -> {
            TopBar(configuration = myLearningScreenTopBar)
        }
    }
}