package com.example.pan.presentation.views.main.profile_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pan.R

@Composable
fun ProfilePicture(
    photoUrl: String?
) {
    val model = photoUrl ?: R.drawable.avatar
    val painter = rememberAsyncImagePainter(model = model)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .size(152.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}