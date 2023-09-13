package com.example.pan.presentation.views.main.main_page

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(MainPageState())
    val state = _state.asStateFlow()

}