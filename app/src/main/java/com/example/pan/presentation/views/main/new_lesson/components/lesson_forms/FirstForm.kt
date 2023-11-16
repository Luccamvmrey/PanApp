package com.example.pan.presentation.views.main.new_lesson.components.lesson_forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pan.core.StringConstants
import com.example.pan.core.StringConstants.CONTENT
import com.example.pan.core.StringConstants.LESSON_TITLE
import com.example.pan.core.StringConstants.NEXT
import com.example.pan.core.StringConstants.VIDEO_NOT_OBLIGATORY
import com.example.pan.core.StringConstants.VIDEO_URL
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.presentation.views.components.LargeSpacer
import com.example.pan.presentation.views.components.PanText
import com.example.pan.presentation.views.components.PanTextField
import com.example.pan.presentation.views.components.SmallSpacer
import com.example.pan.presentation.views.main.new_lesson.NewLessonViewModel

@Composable
fun FirstForm(
    viewModel: NewLessonViewModel = hiltViewModel(),
    onSubmit: (Lesson) -> Unit
) {
    var lessonTitle by remember {
        mutableStateOf(StringConstants.NO_VALUE)
    }
    var videoUrl by remember {
        mutableStateOf(StringConstants.NO_VALUE)
    }
    var lessonText by remember {
        mutableStateOf(StringConstants.NO_VALUE)
    }
    var prerequisite by remember {
        mutableStateOf(StringConstants.NO_VALUE)
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }

    PanTextField(
        value = lessonTitle,
        onValueChange = { lessonTitle = it },
        labelText = LESSON_TITLE,
        error = viewModel.lessonTitleError,
        modifier = Modifier.fillMaxWidth()
    )

    SmallSpacer()

    PanTextField(
        value = videoUrl,
        onValueChange = { videoUrl = it },
        labelText = VIDEO_URL,
        error = viewModel.videoUrlError,
        modifier = Modifier.fillMaxWidth()
    )
    PanText(
        horizontalArrangement = Arrangement.Start,
        text = VIDEO_NOT_OBLIGATORY,
        style = MaterialTheme.typography.labelSmall
    )

    SmallSpacer()

    PanTextField(
        value = lessonText,
        onValueChange = { lessonText = it },
        labelText = CONTENT,
        error = viewModel.lessonTextError,
        singleLine = false,
        modifier = Modifier.fillMaxWidth()
    )

    SmallSpacer()

    PrerequisitesDropdownMenu(
        currentValue = prerequisite,
        onValueChange = {
            prerequisite = it
        },
        lessonsList = viewModel.lessonsList,
        isExpanded = isExpanded,
        onExpandedChange = {
            isExpanded = it
        }
    )

    LargeSpacer()

    Button(
        onClick = {
            if (
                viewModel.checkFirstForm(
                    lessonTitle = lessonTitle,
                    lessonText = lessonText,
                    videoUrl = videoUrl
                )
            ) {
                val prerequisiteId = viewModel.lessonsList.filter { it.lessonTitle == prerequisite }
                    .map { it.lessonId }
                    .firstOrNull()

                onSubmit(
                    Lesson(
                        lessonTitle = lessonTitle,
                        videoUrl = videoUrl,
                        lessonText = lessonText,
                        prerequisite = prerequisiteId
                    )
                )
            }
        }
    ) {
        Text(text = NEXT)
    }
}