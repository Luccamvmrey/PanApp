package com.example.pan.presentation.views.main.new_lesson.components

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
import com.example.pan.core.StringConstants.ADD
import com.example.pan.core.StringConstants.CONTENT
import com.example.pan.core.StringConstants.ENTER_LESSON_TITLE
import com.example.pan.core.StringConstants.LESSON_TITLE
import com.example.pan.core.StringConstants.NO_VALUE
import com.example.pan.core.StringConstants.VIDEO_NOT_OBLIGATORY
import com.example.pan.core.StringConstants.VIDEO_URL
import com.example.pan.domain.models.lesson.Lesson
import com.example.pan.presentation.views.components.PanText
import com.example.pan.presentation.views.components.PanTextField
import com.example.pan.presentation.views.components.SmallMediumSpacer
import com.example.pan.presentation.views.components.SmallSpacer
import com.example.pan.presentation.views.main.new_lesson.NewLessonViewModel

@Composable
fun NewLessonForm(
    viewModel: NewLessonViewModel = hiltViewModel(),
    onSubmit: (Lesson) -> Unit
) {
    var lessonTitle by remember {
        mutableStateOf(NO_VALUE)
    }
    var videoUrl by remember {
        mutableStateOf(NO_VALUE)
    }
    var lessonText by remember {
        mutableStateOf(NO_VALUE)
    }


    PanText(
        horizontalArrangement = Arrangement.Start,
        text = ENTER_LESSON_TITLE,
        style = MaterialTheme.typography.bodySmall
    )
    PanTextField(
        value = lessonTitle,
        onValueChange = { lessonTitle = it },
        labelText = LESSON_TITLE,
        error = viewModel.lessonTitleError,
        modifier = Modifier.fillMaxWidth()
    )

    SmallSpacer()

    PanText(
        horizontalArrangement = Arrangement.Start,
        text = VIDEO_NOT_OBLIGATORY,
        style = MaterialTheme.typography.bodySmall
    )
    PanTextField(
        value = videoUrl,
        onValueChange = { videoUrl = it },
        labelText = VIDEO_URL,
        error = viewModel.videoUrlError,
        modifier = Modifier.fillMaxWidth()
    )

    SmallSpacer()

    PanText(
        horizontalArrangement = Arrangement.Start,
        text = CONTENT,
        style = MaterialTheme.typography.bodySmall
    )
    PanTextField(
        value = lessonText,
        onValueChange = { lessonText = it },
        labelText = CONTENT,
        error = viewModel.lessonTextError,
        singleLine = false,
        modifier = Modifier.fillMaxWidth()
    )

    SmallMediumSpacer()

    Button(
        onClick = { /*TODO*/ }
    ) {
        Text(text = ADD)
    }
}