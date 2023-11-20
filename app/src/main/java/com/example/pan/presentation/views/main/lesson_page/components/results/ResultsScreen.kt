package com.example.pan.presentation.views.main.lesson_page.components.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pan.presentation.views.components.MediumSpacer

@Composable
fun ResultsScreen(
    successCount: Int,
    totalQuestions: Int,
    onContinue: () -> Unit
) {
    val buttonColors = ButtonDefaults.elevatedButtonColors(
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary
    )

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = "Você acertou $successCount de $totalQuestions perguntas!",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )

                MediumSpacer()

                ElevatedButton(
                    colors = buttonColors,
                    onClick = {
                        onContinue()
                    }
                ) {
                    Text(
                        text = "Continuar",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}