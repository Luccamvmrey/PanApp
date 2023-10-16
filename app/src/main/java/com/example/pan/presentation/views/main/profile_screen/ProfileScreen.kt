package com.example.pan.presentation.views.main.profile_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pan.R
import com.example.pan.core.StringConstants.INVISIBLE_PROFILE
import com.example.pan.core.StringConstants.LOGOUT
import com.example.pan.core.StringConstants.POINTS
import com.example.pan.core.StringConstants.WATCHED_LESSONS
import com.example.pan.core.StringConstants.WELCOME_USER
import com.example.pan.domain.models.user.User
import com.example.pan.presentation.ui.theme.spacing
import com.example.pan.presentation.views.components.ContentHolder
import com.example.pan.presentation.views.components.MediumSpacer
import com.example.pan.presentation.views.components.SmallMediumSpacer
import com.example.pan.presentation.views.components.SmallSpacer
import com.example.pan.presentation.views.main.profile_screen.components.ProfileScreenCard

@Composable
fun ProfileScreen(
    user: User,
    isProfileInvisibleChecked: Boolean,
    onLogout: () -> Unit,
    onProfileInvisibleCheck: (Boolean) -> Unit,
) {
    val model = user.photoUrl ?: R.drawable.avatar
    val completedLessons = user.completedLessons?.size ?: 0
    val painter = rememberAsyncImagePainter(model = model)

    ContentHolder(
        verticalArrangement = Arrangement.Top,
        verticalPadding = MaterialTheme.spacing.medium,
        horizontalPadding = MaterialTheme.spacing.large
    ) {
//        AsyncImage(
//            model = model,
//            contentDescription = null,
//            modifier = Modifier
//                .size(152.dp)
//                .clip(CircleShape),
//            contentScale = ContentScale.Crop
//        )
        Image(
            painter = painter,
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

        ProfileScreenCard(
            title = WATCHED_LESSONS,
            text = completedLessons.toString()
        )

        SmallSpacer()

        ProfileScreenCard(
            title = POINTS,
            text = user.points.toString()
        )

        MediumSpacer()
        Divider()
        SmallSpacer()

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onProfileInvisibleCheck(!isProfileInvisibleChecked)
                }
        ) {
            Text(
                text = INVISIBLE_PROFILE,
                style = MaterialTheme.typography.titleMedium
            )
            Switch(
                checked = isProfileInvisibleChecked,
                onCheckedChange = { onProfileInvisibleCheck(it) }
            )
        }

        SmallSpacer()
        Divider()
        SmallSpacer()

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onLogout()
                }
        ) {
            Text(
                text = LOGOUT,
                style = MaterialTheme.typography.titleMedium
            )
            Icon(
                imageVector = Icons.Outlined.Logout,
                contentDescription = null
            )
        }
    }
}