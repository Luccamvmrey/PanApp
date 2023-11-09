package com.example.pan.presentation.views.main.main_page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.presentation.views.main.components.BottomNavigationBar
import com.example.pan.presentation.views.main.components.bottomNavigationItems
import com.example.pan.presentation.views.main.main_page.components.GetUser
import com.example.pan.presentation.views.main.main_page.components.MainPageContent
import com.example.pan.presentation.views.main.main_page.components.MainPageTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPageScreen(
    viewModel: MainPageViewModel = hiltViewModel(),
    navController: NavController
) {
    var selectedScreenIndex by rememberSaveable {
        mutableIntStateOf(1)
    }

    LaunchedEffect(
        key1 = viewModel.updateUser
    ) {
        viewModel.getUser()
    }

    Scaffold(
        topBar = {
            MainPageTopBar(
                selectedScreenIndex = selectedScreenIndex,
                navController = navController
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                GetUser { user ->
                    viewModel.getClassesListFromIds(user)

                    MainPageContent(
                        selectedScreenIndex = selectedScreenIndex,
                        user = user,
                        navController = navController
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavigationItems,
                selectedItemIndex = selectedScreenIndex,
                onSelectNewItem = {
                    selectedScreenIndex = it
                }
            )
        }
    )
}