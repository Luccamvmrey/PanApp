package com.example.pan.presentation.views.signin.starting_page

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.ALREADY_HAS_ACCOUNT
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
            delayNavigation {
                navController.navigate(Screen.MainPageScreen.route)
            }
        } else {
            viewModel.setIsHandlingState(false)
        }
    }

    ContentHolder {
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

        // Maybe add this later?
//        SmallMediumSpacer()
//
//        Divider()
//
//        SmallMediumSpacer()
//
//        OutlinedButton(
//            onClick = {
//                viewModel.googleSignIn()
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(
//                text = GOOGLE_SIGNIN,
//                style = MaterialTheme.typography.bodyMedium,
//            )
//        }
    }
}