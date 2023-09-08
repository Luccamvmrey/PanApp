package com.example.pan.presentation.views.signin.starting_page

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.R
import com.example.pan.core.StringConstants
import com.example.pan.core.StringConstants.ALREADY_HAS_ACCOUNT
import com.example.pan.core.StringConstants.SIGNIN
import com.example.pan.core.StringConstants.WELCOME
import com.example.pan.domain.models.Response.Success
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.LargeSpacer
import com.example.pan.presentation.views.signin.starting_page.components.TextPlusButton

@Composable
fun StartingPageScreen(
    viewModel: StartingPageViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value

    val backgroundImageId = if (isSystemInDarkTheme()) {
        R.drawable.starting_page_deco_dark
    } else {
        R.drawable.starting_page_deco_light
    }

    // TODO: Move this to SplashScreen when its created
    LaunchedEffect(
        key1 = state.getUserResponse
    ) {
        viewModel.getLoggedUser()
        val getUserResponse = state.getUserResponse

        if (getUserResponse is Success) {
            val user = getUserResponse.data

            // TODO: Navigate to main screen with userId as parameter
        }
    }

    ContentHolder(
        backgroundImage = backgroundImageId
    ) {
        Text(
            text = WELCOME,
            style = MaterialTheme.typography.titleLarge
        )

        LargeSpacer()

        TextPlusButton(
            text = ALREADY_HAS_ACCOUNT,
            buttonText = SIGNIN
        ) {
            // TODO: Navigate to login screen
        }
    }
}