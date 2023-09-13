package com.example.pan.presentation.views.signin.starting_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.ALREADY_HAS_ACCOUNT
import com.example.pan.core.StringConstants.GOOGLE_SIGNIN
import com.example.pan.core.StringConstants.NEW_ACCOUNT
import com.example.pan.core.StringConstants.SIGNIN
import com.example.pan.core.StringConstants.SIGNUP
import com.example.pan.core.StringConstants.WELCOME
import com.example.pan.core.delayNavigation
import com.example.pan.domain.models.Response.Success
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.LargeSpacer
import com.example.pan.presentation.views.components.MediumSpacer
import com.example.pan.presentation.views.components.SmallMediumSpacer
import com.example.pan.presentation.views.signin.starting_page.components.TextPlusButton

@Composable
fun StartingPageScreen(
    viewModel: StartingPageViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value

    // TODO: Move this to SplashScreen when its created
    LaunchedEffect(
        key1 = state.getUserResponse
    ) {
        viewModel.getLoggedUser()
        val getUserResponse = state.getUserResponse

        if (getUserResponse is Success) {
            val user = getUserResponse.data
            val userId = user.userId

            userId?.run {
                delayNavigation {
                    navController.navigate(
                        Screen.MainPageScreen.withArgs(userId)
                    )
                }
            }
        } else {
            viewModel.setIsHandlingState(false)
        }
    }

    ContentHolder (
        verticalPadding = 154.dp,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = WELCOME,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        LargeSpacer()

        TextPlusButton(
            text = ALREADY_HAS_ACCOUNT,
            buttonText = SIGNIN
        ) {
            navController.navigate(Screen.LoginScreen.route)
        }

        MediumSpacer()

        TextPlusButton(
            text = NEW_ACCOUNT,
            buttonText = SIGNUP
        ) {
            navController.navigate(Screen.SignupScreen.route)
        }

        SmallMediumSpacer()

        Divider()

        SmallMediumSpacer()

        OutlinedButton(
            onClick = {
                // TODO: Sign in with Google
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = GOOGLE_SIGNIN,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}