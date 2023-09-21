package com.example.pan.presentation.views.main.main_page.components.profile_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pan.R
import com.example.pan.core.StringConstants.POINTS
import com.example.pan.core.StringConstants.WATCHED_LESSONS
import com.example.pan.core.StringConstants.WELCOME_USER
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.ExtraSmallSpacer
import com.example.pan.presentation.views.components.MediumSpacer
import com.example.pan.presentation.views.components.SmallMediumSpacer
import com.example.pan.presentation.views.components.SmallSpacer

@Composable
fun ProfileScreen(
    user: User,
    onLogout: () -> Unit
) {
    val model = user.photoUrl ?: R.drawable.avatar
    val completedLessons = user.completedLessons?.size ?: 0

    ContentHolder(
        verticalArrangement = Arrangement.Top,
        verticalPadding = MaterialTheme.spacing.medium,
        horizontalPadding = MaterialTheme.spacing.large
    ) {
        AsyncImage(
            model = model,
            contentDescription = null,
            modifier = Modifier
                .size(152.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        SmallMediumSpacer()

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = WELCOME_USER.format(user.name),
                style = MaterialTheme.typography.titleMedium
            )
        }

        SmallSpacer()

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            user.email?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        SmallMediumSpacer()

        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.smallMedium,
                        vertical = MaterialTheme.spacing.small
                    )
            ) {
                Text(
                    text = WATCHED_LESSONS,
                    style = MaterialTheme.typography.titleMedium
                )

                ExtraSmallSpacer()

                Text(
                    text = completedLessons.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        SmallSpacer()

        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.smallMedium,
                        vertical = MaterialTheme.spacing.small
                    )
            ) {
                Text(
                    text = POINTS,
                    style = MaterialTheme.typography.titleMedium
                )

                ExtraSmallSpacer()

                Text(
                    text = user.points.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        MediumSpacer()

        Divider()

        MediumSpacer()

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onLogout()
                }
        ) {
            Text(
                text = "Logout",
                style = MaterialTheme.typography.titleMedium
            )
            Icon(
                imageVector = Icons.Outlined.Logout,
                contentDescription = null
            )
        }
        // TODO: Add invisible profile switch.
    }
}