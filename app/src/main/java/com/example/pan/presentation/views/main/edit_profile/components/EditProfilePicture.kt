@file:Suppress("DEPRECATION")

package com.example.pan.presentation.views.main.edit_profile.components

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.pan.core.StringConstants.CHANGE_PICTURE
import com.example.pan.presentation.views.components.SmallSpacer
import com.example.pan.presentation.views.main.profile_screen.components.ProfilePicture

@Composable
fun EditProfilePicture(
    model: Any?,
    photoUrl: String?,
    onBitmapChange: (Bitmap) -> Unit
) {
    val context = LocalContext.current

    var uri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { newUri ->
        uri = newUri
        if (Build.VERSION.SDK_INT < 28) {
            onBitmapChange(
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            )
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, uri!!)
            onBitmapChange(
                ImageDecoder.decodeBitmap(source)
            )
        }
    }

    ProfilePicture(
        model = model,
        photoUrl = photoUrl
    )

    SmallSpacer()

    TextButton(
        onClick = {
            launcher.launch("image/*")
        }
    ) {
        Text(
            text = CHANGE_PICTURE,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}


