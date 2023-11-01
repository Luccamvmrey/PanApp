package com.example.pan.presentation.views.main.my_learning_screen.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.presentation.views.main.main_page.MainPageViewModel

@Composable
fun MyLearningContent(
    viewModel: MainPageViewModel = hiltViewModel(),
    selectedClassId: String,
) {
    if (selectedClassId.isNotEmpty()) {
        viewModel.getLessons(selectedClassId)
        
        GetLessons { _ ->  
            
        }
    } else {
        NoClassSelected()
    }
}


