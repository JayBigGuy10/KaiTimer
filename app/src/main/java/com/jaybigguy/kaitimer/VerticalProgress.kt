package com.jaybigguy.kaitimer

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaybigguy.kaitimer.ui.theme.KaitimerTheme
import kotlin.time.Duration.Companion.minutes

@SuppressLint("DefaultLocale")
@Composable
fun VerticalProgress(
    tvm: TimerViewModel,
    modifier: Modifier = Modifier
) {

    val remaining by tvm.remaining.observeAsState()

    val progress by tvm.progress.observeAsState()

    val mProgress = animateFloatAsState(targetValue = progress!!, label = "")

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.background)
            .width(100.dp)
    ) {
        Box(
            modifier = Modifier
                //.weight(if ((1f - mProgress.value) == 0f) 0.0001f else if ((1f - mProgress.value) == 1f) 0.9999f else 1 - mProgress.value)
                .weight((mProgress.value).coerceIn(0.0001f,0.9999f))
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(text = remaining!!.toComponents { minutes, seconds, _ -> "${String.format("%02d",minutes)}:${String.format("%02d",seconds)}"}, color = MaterialTheme.colorScheme.onBackground)
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .weight((1f-mProgress.value).coerceIn(0.0001f,0.9999f))
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primary
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVerticalProgress() {
    KaitimerTheme {
        VerticalProgress(TimerViewModel(1.5.minutes))
    }
}