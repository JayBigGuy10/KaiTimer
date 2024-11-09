package com.jaybigguy.kaitimer

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun VerticalProgress(
    progress: Float,
    modifier: Modifier = Modifier
) {
    val mProgress = animateFloatAsState(targetValue = progress, label = "")
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.background)
            .width(100.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(if ((1f - mProgress.value) == 0f) 0.0001f else 1 - mProgress.value)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(text = "MM:ss", color = MaterialTheme.colorScheme.onBackground)
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .weight(mProgress.value)
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primary
                )
        )
    }
}