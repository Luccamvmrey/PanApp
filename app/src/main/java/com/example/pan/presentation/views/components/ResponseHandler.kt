package com.example.pan.presentation.views.components

import androidx.compose.runtime.Composable
import com.example.pan.domain.models.Response

@Composable
fun ResponseHandler(
    response: Response<Any>,
    onSuccess: (() -> Unit)? = null,
    onSuccessComposable: @Composable ((data: Any?) -> Unit)? = null,
    onFailure: ((e: Exception?) -> Unit)? = null,
    onFailureComposable: @Composable ((e: Exception?) -> Unit)? = null
) {
    when (response) {
        is Response.Loading -> {
            PanProgressBar()
        }
        is Response.Success -> {
            onSuccess?.invoke()
            onSuccessComposable?.invoke(response.data)
        }
        is Response.Failure -> {
            onFailure?.invoke(response.e)
            onFailureComposable?.invoke(response.e)
        }
    }
}