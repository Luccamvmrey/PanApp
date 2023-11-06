package com.example.pan.presentation.views.main.edit_profile.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.MediumSpacer


@Composable
fun EditProfileForm(
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    bitmap: Bitmap?,
    onBitmapChange: (Bitmap) -> Unit,
    user: User
) {
    ContentHolder(
        verticalArrangement = Arrangement.Top,
        verticalPadding = MaterialTheme.spacing.medium,
        horizontalPadding = MaterialTheme.spacing.large
    ) {
        EditProfilePicture(
            model = bitmap,
            photoUrl = user.photoUrl,
            onBitmapChange = {
                onBitmapChange(it)
            }
        )

        MediumSpacer()

        EditNameAndEmail(
            name = name,
            onNameChange = {
                onNameChange(it)
            },
            email = email,
            onEmailChange = {
                onEmailChange(it)
            },
            user = user
        )
    }
}