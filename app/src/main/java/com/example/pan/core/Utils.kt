package com.example.pan.core

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

fun delayNavigation(navigate: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        navigate()
    }, 500)
}

fun createClassId() : String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..8)
        .map { allowedChars.random() }
        .joinToString("")
}

fun makeToast(
    context: Context,
    message: String,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(
        context,
        message,
        duration
    ).show()
}