package com.example.pan.presentation.views.main.lesson_page.components.lesson

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayer(
    videoUrl: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    val mediaItem = MediaItem
        .Builder()
        .setUri(videoUrl)
        .build()

    val exoPlayer = ExoPlayer
        .Builder(context)
        .build()
        .apply {
            setMediaItem(mediaItem)
            playWhenReady = true
            prepare()
        }

    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
            }
        }
    )

    DisposableEffect(
        key1 = Unit,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_RESUME -> {
                        exoPlayer.play()
                    }
                    Lifecycle.Event.ON_PAUSE -> {
                        exoPlayer.pause()
                    }
                    else -> Unit
                }
            }
            val lifecycle = lifecycleOwner.value.lifecycle
            lifecycle.addObserver(observer)

            onDispose {
                exoPlayer.release()
                lifecycle.removeObserver(observer)
            }
        }
    )
}