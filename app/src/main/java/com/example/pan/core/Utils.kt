package com.example.pan.core

import android.os.Handler
import android.os.Looper

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