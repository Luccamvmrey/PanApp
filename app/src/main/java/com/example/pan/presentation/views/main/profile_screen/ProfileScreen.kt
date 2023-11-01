package com.example.pan.presentation.views.main.profile_screen

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.core.StringConstants.LOGOUT
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.main.main_page.MainPageViewModel
import com.example.pan.presentation.views.main.profile_screen.components.RowTextButton
import com.example.pan.presentation.views.main.profile_screen.components.UserProfile

@Composable
fun ProfileScreen(
    viewModel: MainPageViewModel = hiltViewModel(),
    user: User,
) {
    val activity = LocalContext.current as Activity

    ContentHolder(
        verticalArrangement = Arrangement.Top,
        verticalPadding = MaterialTheme.spacing.medium,
        horizontalPadding = MaterialTheme.spacing.large
    ) {
        UserProfile(user = user)

        RowTextButton(
            text = LOGOUT,
            onClick = {
                viewModel.signOut()
                activity.finishAndRemoveTask()
            }
        )
    }
}