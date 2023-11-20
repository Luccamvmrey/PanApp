package com.example.pan.presentation.views.main.new_lesson

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pan.core.delayNavigation
import com.example.pan.presentation.navigation.Screen
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.TopBar
import com.example.pan.presentation.views.components.newLessonScreenTopBar
import com.example.pan.presentation.views.main.new_lesson.components.AddLesson
import com.example.pan.presentation.views.main.new_lesson.components.GetClass
import com.example.pan.presentation.views.main.new_lesson.components.GetLessonsList
import com.example.pan.presentation.views.main.new_lesson.components.lesson_forms.NewLessonForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewLessonScreen(
    viewModel: NewLessonViewModel = hiltViewModel(),
    navController: NavController
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Scaffold(
        topBar = {
            TopBar(
                extraTitle = " de ${viewModel.panClass.className}",
                configuration = newLessonScreenTopBar
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            indication = null,
                            interactionSource = interactionSource
                        ) { focusManager.clearFocus() }
                        .background(
                            color = MaterialTheme.colorScheme.background
                        )
                        .padding(
                            vertical = MaterialTheme.spacing.large,
                            horizontal = MaterialTheme.spacing.medium
                        )
                        .verticalScroll(scrollState)
                ) {
                    NewLessonForm { newLesson ->
                        viewModel.addLesson(newLesson)
                    }
                }
            }
        }
    )
    GetClass()
    GetLessonsList()
    AddLesson {
        delayNavigation {
            navController.navigate(
                Screen.MainPageScreen.route
            )
        }
    }
}