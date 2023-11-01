package com.example.pan.presentation.views.components

import androidx.compose.runtime.Composable
import com.example.pan.domain.models.Response
import com.example.pan.domain.models.Response.*

@Composable
fun ResponseHandler(
    response: Response<Any>,
    onSuccess: ((data: Any?) -> Unit)? = null,
    onSuccessComposable: @Composable ((data: Any?) -> Unit)? = null,
    onFailure: ((e: Exception?) -> Unit)? = null,
    onFailureComposable: @Composable ((e: Exception?) -> Unit)? = null
) {
    when (response) {
        is Loading -> {
            PanProgressBar()
        }
        is Success -> {
            onSuccess?.invoke(response.data)
            onSuccessComposable?.invoke(response.data)
        }
        is Failure -> {
            onFailure?.invoke(response.e)
            onFailureComposable?.invoke(response.e)
        }
        else -> {}
    }
}