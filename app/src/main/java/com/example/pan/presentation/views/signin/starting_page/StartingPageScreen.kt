package com.example.pan.presentation.views.signin.starting_page

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.pan.core.StringConstants.ALREADY_HAS_ACCOUNT
import com.example.pan.core.StringConstants.NEW_ACCOUNT
import com.example.pan.core.StringConstants.SIGNIN
import com.example.pan.core.StringConstants.SIGNUP
import com.example.pan.core.StringConstants.WELCOME
import com.example.pan.core.delayNavigation
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.LargeSpacer
import com.example.pan.presentation.views.components.MediumSpacer
import com.example.pan.presentation.views.signin.starting_page.components.GetUser
import com.example.pan.presentation.views.signin.starting_page.components.TextPlusButton

@Composable
fun StartingPageScreen(
    navController: NavController
) {
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
    }
    GetUser {
        delayNavigation {
            navController.navigate(Screen.MainPageScreen.route)
        }
    }
}