package com.jaybigguy.kaitimer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.time.Duration

@Composable
fun TimerChip(duration: Duration){

    //val timerLength: Duration = duration

    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSurfaceVariant),
        modifier = Modifier.fillMaxWidth().clickable { /*TODO*/ }) {
        Row(modifier = Modifier.fillMaxWidth().height(100.dp).padding(start = 16.dp, end = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column (modifier = Modifier.fillMaxHeight().fillMaxWidth(0.5F), verticalArrangement = Arrangement.Center) {
                Text(text = duration.toComponents { minutes, seconds, _ -> "${minutes}:${seconds}"  }, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.background)
                Text(text = "Item Text", color = MaterialTheme.colorScheme.background)
            }
            Column (modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                    Icon(painter = painterResource(R.drawable.running_with_errors), "Play", tint = MaterialTheme.colorScheme.onError)
                }
                Button(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.PlayArrow, "Play")
                }
            }

        }
    }

}