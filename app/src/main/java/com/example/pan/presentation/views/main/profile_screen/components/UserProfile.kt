package com.example.pan.presentation.views.main.profile_screen.components

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.views.components.MediumSpacer
import com.example.pan.presentation.views.components.SmallMediumSpacer
import com.example.pan.presentation.views.components.SmallSpacer

@Composable
fun UserProfile(
    user: User
) {
    ProfilePicture(photoUrl = user.photoUrl)

    SmallMediumSpacer()

    NameAndEmail(
        name = user.name!!,
        email = user.email!!
    )

    SmallMediumSpacer()

    UserStats(user = user)

    MediumSpacer()
    Divider()
    SmallSpacer()
}